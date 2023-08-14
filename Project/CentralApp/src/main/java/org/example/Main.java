package org.example;

public class Main {
    public static void main(String[] args){
        String bootstrapServers = "localhost:9092";
        String groupId = "havelsan-group";
        String topic = "havelsan-topic";

        IKafkaConsumer kafkaConsumerCentral = new KafkaConsumerCentral(bootstrapServers, topic, groupId);
        kafkaConsumerCentral.consume();
        kafkaConsumerCentral.close();
    }
}
