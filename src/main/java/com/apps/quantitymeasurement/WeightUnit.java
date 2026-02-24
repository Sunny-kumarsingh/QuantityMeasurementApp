package com.apps.quantitymeasurement;

/**
 * Weight units with KILOGRAM as base unit.
 */
public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592),
    MILLIGRAM(0.001),
    TONNE(1_000_000.0);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}