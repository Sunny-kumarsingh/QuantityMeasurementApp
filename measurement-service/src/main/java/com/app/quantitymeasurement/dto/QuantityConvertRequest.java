package com.app.quantitymeasurement.dto;

import lombok.Data;

@Data
public class QuantityConvertRequest {
    private double value;
    private String fromUnit;
    private String toUnit;
    private String type;
}