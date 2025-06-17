import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class SimpleKafkaProducer {

    public static void main(String[] args) {
        // Set Kafka broker address
        String bootstrapServers = "localhost:9092";
        String topicName = "my-topic";

        // Producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // Create the Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Create a message (record)
        String key = "user1";
        String value = "Hello Kafka!";
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

        try {
            // Send message synchronously and get metadata
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Sent message to topic: " + metadata.topic() +
                               ", partition: " + metadata.partition() +
                               ", offset: " + metadata.offset());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // Close the producer
            producer.close();
        }
    }
}
