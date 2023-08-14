package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerCentral implements IKafkaConsumer {

    private String bootstrapServers;
    private String topic;
    private String groupId;
    private Properties properties;
    private Producer<String, String> producer;
    private ProducerRecord<String, String> record;
    private KafkaConsumer<String, String> consumer;
    private List<KafkaData> kafkaDataList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

     KafkaConsumerCentral(String bootstrapServers, String topic, String groupId) {
         this.bootstrapServers = bootstrapServers;
         this.topic = topic;
         this.groupId = groupId;
        setConfig(bootstrapServers, topic, groupId);
    }

    @Override
    public void setConfig(String bootstrapServers, String topic, String groupId) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));

    }
    @Override
    public void consume() {

        int sensorCount = 2;
        while (sensorCount > 0) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    KafkaData kafkaData = objectMapper.readValue(record.value(), KafkaData.class);
                    kafkaDataList.add(kafkaData);
                    sensorCount--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Utils.calculateTargetLocation(kafkaDataList);
    }

    @Override
    public void close() {
        consumer.close();
    }

}
