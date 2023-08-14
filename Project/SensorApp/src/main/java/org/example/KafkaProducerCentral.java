package org.example;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerCentral implements IKafkaProducer {

    String bootstrapServers;
    String topic;
    Properties properties;
    Producer<String, String> producer;
    ProducerRecord<String, String> record;

     KafkaProducerCentral(String bootstrapServers, String topic) {
        setConfig(bootstrapServers, topic);
    }

    @Override
    public void setConfig(String bootstrapServers, String topic) {
        this.bootstrapServers = bootstrapServers;
        this.topic = topic;
        properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(properties);
    }
    @Override
    public void send(String key, String value) {
        record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    @Override
    public void close() {
        producer.close();
    }

}
