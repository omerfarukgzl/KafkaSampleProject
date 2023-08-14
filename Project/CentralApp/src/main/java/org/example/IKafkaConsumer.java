package org.example;

public interface IKafkaConsumer {
    void setConfig(String bootstrapServers, String topic, String groupId);
    void consume();
    void close();
}
