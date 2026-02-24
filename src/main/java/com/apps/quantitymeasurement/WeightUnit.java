package com.apps.quantitymeasurement;


public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    /** Convert value in this unit to base unit (kilogram) */
    public double toKilogram(double value) {
        return value * toKgFactor;
    }

    /** Convert value from base unit (kilogram) to this unit */
    public double fromKilogram(double kgValue) {
        return kgValue / toKgFactor;
    }

    public double getConversionFactor() {
        return toKgFactor;
    }
}