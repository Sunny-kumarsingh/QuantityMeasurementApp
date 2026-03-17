package com.app.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String error;

    public QuantityMeasurementEntity(String operation, String operand1,
                                     String operand2, String result) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.error = null;
    }

    public QuantityMeasurementEntity(String error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }
}