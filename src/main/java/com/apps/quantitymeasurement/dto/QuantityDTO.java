package com.apps.quantitymeasurement.dto;

import java.io.Serializable;

public class QuantityDTO implements Serializable {
    public double value;
    public String unit;
    public String measurementType;
    public double result;

    public QuantityDTO() {}

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    @Override
    public String toString() {
        return value + " " + unit + " (" + measurementType + ")";
    }
}