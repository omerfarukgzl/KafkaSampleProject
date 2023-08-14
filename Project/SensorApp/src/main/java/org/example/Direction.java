package org.example;

public enum Direction {
    XPOSITIVE("X Pozitif Ekseninde"),
    XNEGATIVE("X Negatif Ekseninde");

    private final String value;
    Direction(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
