package com.app.quantitymeasurement.app;

import com.app.quantitymeasurement.controller.QuantityMeasurementController;

import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.app.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurement.util.ApplicationConfig;


public class QuantityMeasurementApp 
{
	

    public static void main(String[] args) 
    {
    	
    	
    	
    	 // create repository
    	IQuantityMeasurementRepository repository;

    	if (ApplicationConfig.getRepositoryType().equals("database")) {
    	    repository = new QuantityMeasurementDatabaseRepository();
    	} else {
    	    repository = QuantityMeasurementCacheRepository.getInstance();
    	}
        // create service
  
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

        // create controller
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        // start demo
        controller.runDemo();
    	
    	
    	
    	
    }
}