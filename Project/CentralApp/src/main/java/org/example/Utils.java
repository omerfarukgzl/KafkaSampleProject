package org.example;

import java.util.List;

public class Utils {

   public static void calculateTargetLocation(List<KafkaData> kafkaDataList) {
        // eğimleri bilinen doğru denklemlerinin kesim noktasını bulma

        double agent1;
        double agent2;
        double targetX;
        double targetY;
        Integer x1 = kafkaDataList.get(0).getSensorX();
        Integer y1 = kafkaDataList.get(0).getSensorY();
        Integer x2 = kafkaDataList.get(1).getSensorX();
        Integer y2 = kafkaDataList.get(1).getSensorY();
        agent1 =Math.toRadians(kafkaDataList.get(0).getAgent());
        agent2 =Math.toRadians(kafkaDataList.get(1).getAgent());
        Double m1;
        Double m2;

        if((kafkaDataList.get(0).getAgent()>90 && kafkaDataList.get(0).getAgent()<270))
              m1 = 1/Math.tan(agent1);
        else
             m1 = Math.tan(agent1);

        if((kafkaDataList.get(1).getAgent()>90 && kafkaDataList.get(1).getAgent()<270))
             m2 = 1/Math.tan(agent2);
        else
             m2 = Math.tan(agent2);

        if (Math.abs(agent1 - agent2) < 1e-6) {
             System.out.println("Sensörlerin eğim açıları birbirine eşit. Hesaplama yapılamaz.");
             return;
        }
        targetX =Math.round((m1*x1 - m2*x2 + y2 - y1)/(m1-m2));
        targetY = Math.round(m1*(targetX-x1)+y1);
        System.out.println("Hedef ("+ targetX + "," + targetY +") noktasında bulunmaktadır.");
    }
}
