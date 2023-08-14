package org.example;

public class KafkaData {
    private String sensorName;
    private Integer sensorX;
    private Integer sensorY;
    private Double agent;// Y pozitif eksene göre saat yönündeki açı Kerteriz bilgisi

    KafkaData(String sensorName, Integer sensorX, Integer sensorY, Double agent) {
        this.sensorName = sensorName;
        this.sensorX = sensorX;
        this.sensorY = sensorY;
        this.agent = agent;
    }

    public String getSensorName() {
        return sensorName;
    }
    public Integer getSensorX() {
        return sensorX;
    }
    public Integer getSensorY() {
        return sensorY;
    }
    public Double getAgent() {
        return agent;
    }
}
