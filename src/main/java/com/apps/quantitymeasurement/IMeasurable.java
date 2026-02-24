package com.apps.quantitymeasurement;

/**
 * Common contract for all measurable units.
 */
public interface IMeasurable {

    double getConversionFactor();          // factor relative to base unit

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();
}