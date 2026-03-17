package com.app.quantitymeasurement.unit;
public interface IMeasurable {
	  // Convert a value to base unit representation
    double convertToBaseUnit(double value);
    
    // Convert from base unit back to current unit
    double convertFromBaseUnit(double baseValue);

    String getUnitName();
    
    MeasurementType getMeasurementType();

    default boolean supportsAddition() {
        return true;
    }

    default boolean supportsSubtraction() {
        return true;
    }

    default boolean supportsDivision() {
        return true;
    }

    default boolean supportsMultiplication() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        switch (operation.toLowerCase()) {
            case "addition":
                if (!supportsAddition())
                    throw new UnsupportedOperationException(getUnitName() + " does not support addition");
                break;

            case "subtraction":
                if (!supportsSubtraction())
                    throw new UnsupportedOperationException(getUnitName() + " does not support subtraction");
                break;

            case "division":
                if (!supportsDivision())
                    throw new UnsupportedOperationException(getUnitName() + " does not support division");
                break;

            case "multiplication":
                if (!supportsMultiplication())
                    throw new UnsupportedOperationException(getUnitName() + " does not support multiplication");
                break;

            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    static IMeasurable fromUnitName(String measurementType, String unitName) {

        switch (measurementType.toUpperCase()) {

            case "LENGTH":
                return LengthUnit.valueOf(unitName.toUpperCase());

            case "WEIGHT":
                return WeightUnit.valueOf(unitName.toUpperCase());

            case "VOLUME":
                return VolumeUnit.valueOf(unitName.toUpperCase());

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unitName.toUpperCase());

            default:
                throw new IllegalArgumentException(
                        "Unknown measurement type: " + measurementType);
        }
    }
   
	}