package org.example;

public class KafkaData {
    private String sensorName;
    private Integer sensorX;
    private Integer sensorY;
    private Double agent;

    KafkaData() {
    }

    KafkaData(String sensorName, Integer sensorX, Integer sensorY, Double agent) {
        this.sensorName = sensorName;
        this.sensorX = sensorX;
        this.sensorY = sensorY;
        this.agent = agent;
    }
    // getters
    public String getSensorName() {
        return sensorName;
    }
    // getters
    public Integer getSensorX() {
        return sensorX;
    }
    public Integer getSensorY() {
        return sensorY;
    }
    public Double getAgent() {
        return agent;
    }
    // setters
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
    public void setSensorX(Integer sensorX) {
        this.sensorX = sensorX;
    }
    public void setSensorY(Integer sensorY) {
        this.sensorY = sensorY;
    }
    public void setAgent(Double agent) {
        this.agent = agent;
    }

}
