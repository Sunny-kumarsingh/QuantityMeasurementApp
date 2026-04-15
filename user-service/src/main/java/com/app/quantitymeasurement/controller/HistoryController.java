package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.HistoryResponseDTO;
import com.app.quantitymeasurement.model.UserEntity;
import com.app.quantitymeasurement.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    private UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/save")
    public void saveHistory(@RequestBody com.app.quantitymeasurement.dto.SaveHistoryRequest request) {
        UserEntity user = new UserEntity();
        user.setId(request.getUserId());
        historyService.saveHistory(user, request.getOperationType(), request.getMeasurementType(),
            request.getValue1(), request.getUnit1(), request.getValue2(), request.getUnit2(),
            request.getResultValue(), request.getResultUnit(), request.getResultText(), 
            request.isError(), request.getErrorMessage());
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponseDTO>> getAllHistory(
            @RequestParam(defaultValue = "50") int limit) {
        return ResponseEntity.ok(historyService.getUserHistory(getCurrentUser(), limit));
    }

    @GetMapping("/operation/{operationType}")
    public ResponseEntity<List<HistoryResponseDTO>> getHistoryByOperation(
            @PathVariable String operationType) {
        return ResponseEntity.ok(historyService.getUserHistoryByOperation(getCurrentUser(), operationType));
    }

    @GetMapping("/type/{measurementType}")
    public ResponseEntity<List<HistoryResponseDTO>> getHistoryByMeasurementType(
            @PathVariable String measurementType) {
        return ResponseEntity.ok(historyService.getUserHistoryByMeasurementType(getCurrentUser(), measurementType));
    }

    @GetMapping("/errors")
    public ResponseEntity<List<HistoryResponseDTO>> getErrorHistory() {
        return ResponseEntity.ok(historyService.getUserErrorHistory(getCurrentUser()));
    }

    @GetMapping("/count/{operationType}")
    public ResponseEntity<Map<String, Object>> getOperationCount(@PathVariable String operationType) {
        Map<String, Object> response = new HashMap<>();
        response.put("operationType", operationType);
        response.put("count", historyService.getOperationCount(getCurrentUser(), operationType));
        return ResponseEntity.ok(response);
    }
}