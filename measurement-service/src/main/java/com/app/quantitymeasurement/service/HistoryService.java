package com.app.quantitymeasurement.service;

import org.springframework.stereotype.Service;
import com.app.quantitymeasurement.client.UserServiceClient;
import com.app.quantitymeasurement.dto.SaveHistoryRequest;
import com.app.quantitymeasurement.model.UserEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final UserServiceClient userServiceClient;

    public void saveHistory(UserEntity user, String operationType, String measurementType,
                           Double value1, String unit1, Double value2, String unit2,
                           Double resultValue, String resultUnit, String resultText,
                           boolean isError, String errorMessage) {
        
        SaveHistoryRequest req = new SaveHistoryRequest();
        req.setUserId(user.getId());
        req.setOperationType(operationType);
        req.setMeasurementType(measurementType);
        req.setValue1(value1);
        req.setUnit1(unit1);
        req.setValue2(value2);
        req.setUnit2(unit2);
        req.setResultValue(resultValue);
        req.setResultUnit(resultUnit);
        req.setResultText(resultText);
        req.setError(isError);
        req.setErrorMessage(errorMessage);

        userServiceClient.saveHistory(req);
    }
}