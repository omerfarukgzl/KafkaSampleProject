package org.example;

public class Main {
    public static void main(String[] args) {
        IKafkaProducer kafkaProducerCentral = new KafkaProducerCentral("localhost:9092", "havelsan-topic");
        Sensor sensor = new Sensor("Sensor 1",-34,1, kafkaProducerCentral);
        Sensor sensor2 = new Sensor("Sensor 2",-100,-1, kafkaProducerCentral);
        Target target = new Target(-40,-52);
        target.addObserverSensor(sensor);
        target.addObserverSensor(sensor2);
        target.putTarget();
        kafkaProducerCentral.close();
    }

}