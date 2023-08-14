package org.example;

public interface IKafkaProducer {
    void setConfig(String bootstrapServers, String topic);
    void send(String key, String value);
    void close();
}
