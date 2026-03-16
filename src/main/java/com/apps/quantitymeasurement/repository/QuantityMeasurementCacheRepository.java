package com.apps.quantitymeasurement.repository;

import java.util.ArrayList;
import java.util.List;

import com.apps.quantitymeasurement.model.QuantityMeasurementEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Cache repository for storing quantity measurement operations
 */
public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
	
	private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementCacheRepository.class);

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return cache;
    }
}