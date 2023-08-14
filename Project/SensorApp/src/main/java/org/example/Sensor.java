package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Sensor implements IObserver {
    private String name;
    private Integer x;
    private Integer y;
    private Direction detectedTargetDirectionX;
    private IKafkaProducer kafkaProducer;

    public Sensor(String name, Integer x, Integer y, IKafkaProducer kafkaProducer) {
        if(x >1000 || x<-1000 || y >1000 || y<-1000)
            throw new IllegalArgumentException("Sensor konumu 1000x1000 koordinat sisteminin dışında olamaz.");
        this.name = name;
        this.x = x;
        this.y = y;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void detectTarget(Integer xTarget, Integer yTarget) {
        System.out.println(name + "Sensor Hedefi Tespit Etti: ");
        Double angle = calculateLocation(xTarget,yTarget);
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaData kafkaData = new KafkaData(name,x,y,angle);
        String json;
        try {
             json= objectMapper.writeValueAsString(kafkaData);
             System.out.println(name + "için hedefin Y Pozitif ekseninden saat yönündeki açısı " + angle + " derecedir.");
             kafkaProducer.send("sensor",json);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Double calculateLocation(Integer xTarget, Integer yTarget)
    {
        Double xFark = Double.valueOf((x-xTarget));
        Double yFark =Double.valueOf(y-yTarget);

        // hedef sensorun sağında mı solunda mı
        if (xFark<0) {
            xFark = xFark * -1;
            detectedTargetDirectionX = Direction.XPOSITIVE;
        }
        else {
            detectedTargetDirectionX = Direction.XNEGATIVE;
        }

        // hedef sensorun üstünde mi altında mı
        if( yFark<0) {
            yFark = yFark * -1;

            Double ratio = (yFark ==0 || xFark ==0) ? 0.0 : yFark/xFark;
            var angle = Math.atan(ratio);
            angle =90 - Math.toDegrees(angle);// radian to degree
            angle = (detectedTargetDirectionX == Direction.XPOSITIVE) ? 90-angle : 270 + angle;
            return angle;
        }
        else {
            Double ratio = (yFark ==0 || xFark ==0) ? 0.0 : yFark/xFark;
            var angle = Math.atan(ratio);
            angle = Math.toDegrees(angle);// radian to degree
            angle = (detectedTargetDirectionX == Direction.XPOSITIVE) ?  90 + angle : 270 - angle;
            return angle;
        }
    }

    public String getName() {
        return name;
    }
    public Integer getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }
}