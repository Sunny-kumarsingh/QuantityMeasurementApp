package com.app.quantitymeasurement.unit;

import java.util.Objects;


import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> {

	private static final double ROUND_FACTOR = 100000.0;
	private final double value;
	private final U unit;

	private enum ArithmeticOperation {

		ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b), DIVIDE((a, b) -> a / b);

		private final DoubleBinaryOperator operator;

		ArithmeticOperation(DoubleBinaryOperator operator) {
			this.operator = operator;
		}

		double compute(double a, double b) {
			return operator.applyAsDouble(a, b);
		}
	}

	public Quantity(double value, U unit) {

		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (Double.isNaN(value) || Double.isInfinite(value))
			throw new IllegalArgumentException("Invalid numeric value");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public U getUnit() {
		return unit;
	}

	private static double round(double value) {
		return Math.round(value * ROUND_FACTOR) / ROUND_FACTOR;
	}

	private void validateArithmeticOperands(Quantity<?> other, U targetUnit, boolean targetRequired) {

		if (other == null)
			throw new IllegalArgumentException("Operand quantity cannot be null");

		if (this.unit.getClass() != other.unit.getClass())
			throw new IllegalArgumentException("Cannot operate on different measurement categories");

		if (targetRequired && targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");
	}

	private double performBaseArithmetic(Quantity<?> quantity2, ArithmeticOperation operation) {

		double base1 = this.unit.convertToBaseUnit(this.value);
		double base2 = quantity2.unit.convertToBaseUnit(quantity2.value);

		if (operation == ArithmeticOperation.DIVIDE && base2 == 0.0)
			throw new ArithmeticException("Division by zero");

		return operation.compute(base1, base2);
	}

	// Unit conversion

	public Quantity<U> convertTo(U target) {

		if (target == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		if (this.unit == target)
			return this;

		double baseValue = unit.convertToBaseUnit(value);

		double convertedValue = target.convertFromBaseUnit(baseValue);

		// do not round temperature conversions
		if (!(unit instanceof TemperatureUnit)) {
			convertedValue = round(convertedValue);
		}

		return new Quantity<>(convertedValue, target);
	}

//	Arithmetic Operation
//  Add

	@SuppressWarnings("unchecked")
	public Quantity<U> add(Quantity<?> quantity2) {

		if (quantity2 == null)
			throw new IllegalArgumentException("Operand quantity cannot be null");

		unit.validateOperationSupport("addition");
		quantity2.unit.validateOperationSupport("addition");

		validateArithmeticOperands(quantity2, null, false);

		double baseResult = performBaseArithmetic(quantity2, ArithmeticOperation.ADD);

		return new Quantity<>(round(unit.convertFromBaseUnit(baseResult)), unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {

		unit.validateOperationSupport("addition");
		other.unit.validateOperationSupport("addition");

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

		return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

	public static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U targetUnit) {

		if (q1 == null || q2 == null)
			throw new IllegalArgumentException("Operand quantity cannot be null");

		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		q1.unit.validateOperationSupport("addition");
		q2.unit.validateOperationSupport("addition");

		double base1 = q1.unit.convertToBaseUnit(q1.value);
		double base2 = q2.unit.convertToBaseUnit(q2.value);

		return new Quantity<>(round(targetUnit.convertFromBaseUnit(base1 + base2)), targetUnit);
	}

	// Arithmetic operation
	// Subtract

	public Quantity<U> subtract(Quantity<?> quantity2) {

		if (quantity2 == null)
			throw new IllegalArgumentException("Operand quantity cannot be null");

		unit.validateOperationSupport("subtraction");
		quantity2.unit.validateOperationSupport("subtraction");

		validateArithmeticOperands(quantity2, null, false);

		double baseResult = performBaseArithmetic(quantity2, ArithmeticOperation.SUBTRACT);

		return new Quantity<>(round(unit.convertFromBaseUnit(baseResult)), unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

		unit.validateOperationSupport("subtraction");
		other.unit.validateOperationSupport("subtraction");

		validateArithmeticOperands(other, targetUnit, true);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

		return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

//   Arithmetic operation
//   Divide

	public double divide(Quantity<?> quantity2) {

		if (quantity2 == null)
			throw new IllegalArgumentException("Operand quantity cannot be null");

		unit.validateOperationSupport("division");
		quantity2.unit.validateOperationSupport("division");

		validateArithmeticOperands(quantity2, null, false);

		return performBaseArithmetic(quantity2, ArithmeticOperation.DIVIDE);
	}

	// here equality is checked
	// by overriding equals

	
	@Override
	public boolean equals(Object obj) {

	    if (this == obj)
	        return true;

	    if (obj == null || getClass() != obj.getClass())
	        return false;

	    Quantity<?> other = (Quantity<?>) obj;

	    if (this.unit.getMeasurementType() != other.unit.getMeasurementType())
	        return false;

	    double base1 = this.unit.convertToBaseUnit(this.value);
	    double base2 = other.unit.convertToBaseUnit(other.value);

	    // Temperature comparison (use tolerance, no rounding)
	    if (this.unit.getMeasurementType() == MeasurementType.TEMPERATURE) {
	        double EPSILON = 1e-6;
	        return Math.abs(base1 - base2) < EPSILON;
	    }

	    // Other measurements (use rounding)
	    base1 = round(base1);
	    base2 = round(base2);

	    return Double.compare(base1, base2) == 0;
	}

	@Override
	public int hashCode() {

		double baseValue = unit.convertToBaseUnit(value);

		if (unit instanceof TemperatureUnit) {
			baseValue = Math.round(baseValue * 1000) / 1000.0;
		} else {
			baseValue = round(baseValue);
		}

		return Objects.hash(unit.getClass().getName(), baseValue);
	}

	@Override
	public String toString() {
		return String.format("Quantity(%.2f, %s)", value, unit.getUnitName());
	}
}