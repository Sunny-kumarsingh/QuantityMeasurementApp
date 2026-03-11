package com.apps.quantitymeasurement.entity;

import java.io.Serializable;

import com.apps.quantitymeasurement.dto.QuantityDTO;

public class QuantityMeasurementEntity implements Serializable {

    private QuantityDTO operand1;
    private QuantityDTO operand2;
    private String operation;
    private QuantityDTO result;

    public QuantityMeasurementEntity(QuantityDTO op1, QuantityDTO op2,
                                     String operation, QuantityDTO result) {

        this.operand1 = op1;
        this.operand2 = op2;
        this.operation = operation;
        this.result = result;
    }
}