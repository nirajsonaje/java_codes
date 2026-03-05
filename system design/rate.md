# Rate Limiting vs Throttling — The Complete Difference 🚦

This is a **deceptively simple** question that most candidates get partially wrong. Let's nail the exact distinction plus full implementation.

---

## 🧠 The Core Difference in One Line

> **Rate Limiting** = "You're only ALLOWED X requests per minute — excess requests are REJECTED immediately."
> **Throttling** = "You can make unlimited requests, but we SLOW DOWN or QUEUE excess ones — nobody gets rejected."

---

## 🎯 Simple Real-World Analogy

```
RATE LIMITING = Nightclub with strict capacity
  "Only 100 people allowed. 101st person? 
   BOUNCER TURNS YOU AWAY. Come back later."
   → Request REJECTED (429 error)

THROTTLING = Highway with speed limit
  "You CAN drive, but we control HOW FAST.
   Too many cars? We slow everyone down equally."
   → Request DELAYED or QUEUED, not rejected
```

---

## 📊 Side-by-Side Comparison

```
Feature          Rate Limiting          Throttling
──────────────────────────────────────────────────
Action           REJECT excess          DELAY/QUEUE excess
Response         429 Too Many Requests  200 OK (but slower)
User experience  "Try again later"      "Loading..." (slower)
Use case         External APIs          Internal services
Protects         Your server from abuse  Your server from overload
Client control   Client must back off   Server controls the pace
```

---

## 🔧 Rate Limiting — Full Implementation

```java
// Rate Limiter using Redis (Sliding Window algorithm)
@Component
public class RateLimiterFilter implements Filter {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // Config: 100 requests per minute per user
    private static final int MAX_REQUESTS = 100;
    private static final int WINDOW_SECONDS = 60;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String userId = extractUserId(request);
        String key = "rate_limit:" + userId;

        // Atomic increment
        Long requestCount = redisTemplate.opsForValue().increment(key);

        if (requestCount == 1) {
            // First request in window — set expiry
            redisTemplate.expire(key, WINDOW_SECONDS, TimeUnit.SECONDS);
        }

        // Add helpful headers (like Twitter/GitHub APIs do)
        Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        response.setHeader("X-RateLimit-Limit", String.valueOf(MAX_REQUESTS));
        response.setHeader("X-RateLimit-Remaining",
            String.valueOf(Math.max(0, MAX_REQUESTS - requestCount)));
        response.setHeader("X-RateLimit-Reset",
            String.valueOf(System.currentTimeMillis() / 1000 + ttl));

        if (requestCount > MAX_REQUESTS) {
            // ❌ REJECT — Rate limit exceeded
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // 429
            response.setHeader("Retry-After", String.valueOf(ttl));
            response.getWriter().write("""
                {
                  "error": "Rate limit exceeded",
                  "message": "Max 100 requests/minute. Try after %d seconds.",
                  "retryAfter": %d
                }
                """.formatted(ttl, ttl));
            return; // REQUEST REJECTED — never reaches controller
        }

        chain.doFilter(req, res); // ✅ Request allowed
    }
}
```

---

## 🔧 Throttling — Full Implementation

```java
// Throttling using a semaphore — controls CONCURRENCY
@Service
public class ThrottledProductService {

    // Only 50 concurrent requests allowed at any time
    private final Semaphore semaphore = new Semaphore(50);

    // Queue for waiting requests
    private final BlockingQueue<Runnable> requestQueue =
        new LinkedBlockingQueue<>(1000);

    public ProductResponse getProduct(String productId)
            throws InterruptedException {

        // Try to acquire slot — WAIT up to 5 seconds
        boolean acquired = semaphore.tryAcquire(5, TimeUnit.SECONDS);

        if (!acquired) {
            // After 5s still no slot → now reject (timeout)
            throw new ServiceOverloadedException(
                "System busy, please retry"
            );
        }

        try {
            // Got a slot — process (maybe slower under load)
            return productRepo.findById(productId);
        } finally {
            semaphore.release(); // Always release!
        }
    }
}
```

```java
// Throttling using Resilience4j RateLimiter
// (confusingly named, but actually implements throttling behavior)
@Service
public class ExternalApiService {

    // Throttle calls to external payment gateway
    // Max 10 calls per second — excess wait in queue
    @RateLimiter(name = "paymentGateway", fallbackMethod = "paymentFallback")
    public PaymentResult callPaymentGateway(PaymentRequest request) {
        return paymentGateway.process(request);
    }

    public PaymentResult paymentFallback(PaymentRequest request,
                                          RequestNotPermitted ex) {
        // Queue for retry — don't reject
        retryQueue.add(request);
        return PaymentResult.queued("Payment queued, processing shortly");
    }
}
```

```yaml
# Resilience4j throttling config
resilience4j:
  ratelimiter:
    instances:
      paymentGateway:
        limitForPeriod: 10          # 10 calls per period
        limitRefreshPeriod: 1s      # Reset every second
        timeoutDuration: 3s         # Wait 3s for a slot before failing
        # → Excess requests WAIT 3s, not immediately rejected
        # → This is THROTTLING behavior
```

---

## 🌊 The 4 Rate Limiting Algorithms

### 1. Fixed Window Counter (Simplest)
```
|---60s window---|---60s window---|
 0  20  40  60   0  20  40  60

Counter resets every 60 seconds.
Problem: Burst at boundary — 100 at 0:59 + 100 at 1:00 = 200 in 2 seconds!
```

### 2. Sliding Window Log (Most Accurate)
```java
public boolean isAllowed(String userId) {
    String key = "sliding:" + userId;
    long now = System.currentTimeMillis();
    long windowStart = now - 60_000; // 60 seconds ago

    // Remove old entries
    redisTemplate.opsForZSet().removeRangeByScore(key, 0, windowStart);

    // Count requests in window
    Long count = redisTemplate.opsForZSet().size(key);

    if (count < MAX_REQUESTS) {
        // Add current request timestamp
        redisTemplate.opsForZSet().add(key, String.valueOf(now), now);
        redisTemplate.expire(key, 70, TimeUnit.SECONDS);
        return true;
    }
    return false; // Reject
}
```

### 3. Token Bucket (Most Common in Practice)
```
Bucket capacity: 100 tokens
Refill rate: 10 tokens/second

Each request consumes 1 token
If bucket empty → reject
Allows burst (use saved tokens) + sustained rate

Used by: AWS API Gateway, Stripe, GitHub API
```

### 4. Leaky Bucket (Smoothest output — for Throttling)
```
Requests pour IN at any rate (burst OK)
Requests drip OUT at fixed rate

Like a bucket with a small hole:
  - Input: variable/bursty
  - Output: steady, controlled

→ This is THROTTLING! Output rate controlled, not input.
```

---

## 🏗️ Where Each is Used in Real Systems

```
RATE LIMITING used for:
  ✅ Public APIs (GitHub: 5000/hr, Twitter: 300/15min)
  ✅ Login endpoints (prevent brute force)
  ✅ SMS/Email OTP sending (prevent spam)
  ✅ Search endpoints (expensive queries)
  ✅ User-facing APIs (per customer tier)

THROTTLING used for:
  ✅ Internal microservice calls
  ✅ Database connection pools
  ✅ 3rd party API calls (you pay per call)
  ✅ File upload processing
  ✅ Background job processing
  ✅ Message queue consumers
```

---

## 📱 Tiered Rate Limiting (Real API Design)

```java
// Different limits per subscription tier
@Component
public class TieredRateLimiter {

    private Map<String, Integer> tierLimits = Map.of(
        "FREE",       100,   // 100 req/hour
        "BASIC",      1000,  // 1000 req/hour
        "PRO",        10000, // 10,000 req/hour
        "ENTERPRISE", 100000 // 100,000 req/hour
    );

    public boolean isAllowed(String userId) {
        User user = userCache.get(userId);
        int limit = tierLimits.get(user.getTier());

        String key = "rate:" + userId;
        Long count = redisTemplate.opsForValue().increment(key);

        if (count == 1) {
            redisTemplate.expire(key, 1, TimeUnit.HOURS);
        }

        return count <= limit;
    }
}
```

---

## 🎯 Interview Answer (Say This)

> *"Both control traffic flow but differently. **Rate limiting** enforces a hard ceiling — exceed the limit and requests are immediately rejected with a 429 error. The client must back off and retry. **Throttling** controls the rate of processing — excess requests are queued or slowed down, not rejected. Think of rate limiting as a bouncer turning people away, and throttling as a traffic signal slowing cars but letting everyone through eventually. Rate limiting protects against abuse from external clients. Throttling manages load on internal services or expensive third-party calls. In Spring Boot: rate limiting uses Redis counters with a reject response; throttling uses Semaphores, Resilience4j RateLimiter with a timeout, or message queue buffering."*

---

## 🏢 Companies That Ask This

**Razorpay, Paytm, Swiggy, Zomato, Atlassian, Amazon** — any company with public APIs or high-traffic services. Very common in **backend + system design** rounds.

---

## 🔑 Final Takeaways

| | Rate Limiting | Throttling |
|---|---|---|
| **Excess requests** | Rejected (429) | Queued/delayed |
| **Client response** | Error immediately | Slower response |
| **Algorithm** | Token bucket, sliding window | Leaky bucket, semaphore |
| **Best for** | Public API abuse prevention | Internal load control |
| **Spring tool** | Redis + filter | Resilience4j + semaphore |
| **HTTP status** | 429 Too Many Requests | 200 OK (just slower) |

**Interview trap: Many candidates use these terms interchangeably. Knowing the exact difference = instant credibility boost.** 💡