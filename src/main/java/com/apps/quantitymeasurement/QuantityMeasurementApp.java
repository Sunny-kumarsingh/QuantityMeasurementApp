package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Overloaded demo methods
    public static void demonstrateLengthConversion(double value,
                                                    LengthUnit from,
                                                    LengthUnit to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength length,
                                                    LengthUnit to) {
        QuantityLength converted = length.convertTo(to);
        System.out.println(length + " → " + converted);
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);     // 12.0
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);      // 9.0
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);   // 1.0
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(q, LengthUnit.INCHES);
    }
}