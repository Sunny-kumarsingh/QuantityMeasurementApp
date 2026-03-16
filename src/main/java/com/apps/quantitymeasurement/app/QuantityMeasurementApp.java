package com.apps.quantitymeasurement.app; // Updated package name as per UC16

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.model.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
// ... other imports ...
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {
    // 1. Initialize the Logger
	private static final Logger logger =
	        LoggerFactory.getLogger(QuantityMeasurementApp.class);
	
	
    public static void main(String[] args) {
        logger.info("Quantity Measurement Application starting...");

        try {
            // Repository, Service, and Controller initialization
            QuantityMeasurementCacheRepository repository = new QuantityMeasurementCacheRepository();
            QuantityMeasurementServiceImpl service = new QuantityMeasurementServiceImpl(repository);
            QuantityMeasurementController controller = new QuantityMeasurementController(service);

            // Example 1: Comparison
            QuantityDTO q1 = new QuantityDTO(1, QuantityDTO.LengthUnit.FEET);
            QuantityDTO q2 = new QuantityDTO(12, QuantityDTO.LengthUnit.INCHES);
            
            // 2. Replace System.out.println with logger.info
            logger.info("Comparing 1 FEET and 12 INCHES: {}", controller.compare(q1, q2));

            // Example 2: Conversion
            QuantityDTO converted = controller.convert(q1, QuantityDTO.LengthUnit.INCHES);
            logger.info("Conversion result: 1 FEET is {} INCHES", converted.value);

            // ... repeat for other operations ...

        } catch (Exception e) {
            // 3. Log errors professionally
            logger.error("An error occurred during application execution: ", e);
        }
    }
}