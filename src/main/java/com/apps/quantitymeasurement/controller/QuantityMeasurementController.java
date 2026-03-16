package com.apps.quantitymeasurement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;


public class QuantityMeasurementController {
	
	  private static final Logger logger =
	            LoggerFactory.getLogger(QuantityMeasurementController.class);

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO convert(QuantityDTO quantity,
                               QuantityDTO.IMeasurableUnit targetUnit) {

        return service.convert(quantity, targetUnit);
    }

    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }

    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {
        return service.subtract(q1, q2);
    }

    public double divide(QuantityDTO q1, QuantityDTO q2) {
        return service.divide(q1, q2);
    }
}