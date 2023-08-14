package org.example;

public class Target extends Observable {
    private Integer x;
    private Integer y;

    public Target(Integer x, Integer y) {
        if(x >1000 || x<-1000 || y >1000 || y<-1000)
            throw new IllegalArgumentException("Hedef konumu 1000x1000 koordinat sisteminin dışında olamaz.");
        this.x = x;
        this.y = y;
    }

    public void putTarget() {
        notifyObserversSensor(x, y);
    }
}
