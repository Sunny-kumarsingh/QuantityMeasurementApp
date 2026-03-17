package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.unit.IMeasurable;
import com.app.quantitymeasurement.unit.Quantity;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@SuppressWarnings("unused")
	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {

		Quantity<?> quantity1 = convertToQuantity(q1);
		Quantity<?> quantity2 = convertToQuantity(q2);

		return quantity1.equals(quantity2);
	}

	private Quantity<?> convertToQuantity(QuantityDTO dto) {

		IMeasurable unit = IMeasurable.fromUnitName(dto.getMeasurementType(), dto.getUnit());

		return new Quantity<>(dto.getValue(), unit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityDTO convert(QuantityDTO q, String targetUnit) {

	    Quantity<IMeasurable> quantity =
	            (Quantity<IMeasurable>) convertToQuantity(q);

	    IMeasurable target =
	            IMeasurable.fromUnitName(q.getMeasurementType(), targetUnit);

	    Quantity<IMeasurable> result = quantity.convertTo(target);

	    return new QuantityDTO(
	            result.getValue(),
	            result.getUnit().getUnitName(),
	            q.getMeasurementType()
	    );
	}
	@Override
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

	    Quantity<?> quantity1 = convertToQuantity(q1);
	    Quantity<?> quantity2 = convertToQuantity(q2);

	    Quantity<?> result = quantity1.add(quantity2);

	    return new QuantityDTO(
	            result.getValue(),
	            result.getUnit().getUnitName(),
	            q1.getMeasurementType()
	    );
	}

	@Override
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

	    Quantity<?> quantity1 = convertToQuantity(q1);
	    Quantity<?> quantity2 = convertToQuantity(q2);

	    Quantity<?> result = quantity1.subtract(quantity2);

	    return new QuantityDTO(
	            result.getValue(),
	            result.getUnit().getUnitName(),
	            q1.getMeasurementType()
	    );
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {

	    Quantity<?> quantity1 = convertToQuantity(q1);
	    Quantity<?> quantity2 = convertToQuantity(q2);

	    return quantity1.divide(quantity2);
	}
}