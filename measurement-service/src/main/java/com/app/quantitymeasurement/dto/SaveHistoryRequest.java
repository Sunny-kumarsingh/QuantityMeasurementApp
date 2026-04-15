package com.app.quantitymeasurement.dto;

import lombok.Data;

@Data
public class SaveHistoryRequest {
    private Long userId;
    private String operationType;
    private String measurementType;
    private Double value1;
    private String unit1;
    private Double value2;
    private String unit2;
    private Double resultValue;
    private String resultUnit;
    private String resultText;
    private boolean isError;
    private String errorMessage;
}
