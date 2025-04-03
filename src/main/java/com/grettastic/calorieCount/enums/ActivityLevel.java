package com.grettastic.calorieCount.enums;

public enum ActivityLevel {
    MIN(1.2),
    MILD(1.375),
    AVG(1.55),
    HIGH(1.725),
    SUPER(1.9);

    private double value;
    ActivityLevel(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
