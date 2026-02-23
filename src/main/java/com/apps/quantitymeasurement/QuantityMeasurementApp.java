package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // UC1: Feet to Feet
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feet1.equals(feet2) + ")");

        // UC2: Inch to Inch
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inch1.equals(inch2) + ")");

        // UC3: Cross-unit comparison
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("Input: 1.0 ft and 12.0 inch");
        System.out.println("Output: Equal (" + feet.equals(inches) + ")");
    }
}