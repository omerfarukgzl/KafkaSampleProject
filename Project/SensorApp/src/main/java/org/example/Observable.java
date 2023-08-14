package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<IObserver> IObservers;

    Observable() {
        IObservers = new ArrayList<>();
    }

    public void addObserverSensor(IObserver IObserver)
    {
        IObservers.add(IObserver);
    }

    public void notifyObserversSensor(Integer x, Integer y)
    {
        for (IObserver IObserver : IObservers) {
            IObserver.detectTarget(x, y);
        }
    }

}
