package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
            )
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
            )
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
            )
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS),
                LengthUnit.FEET
            )
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS
            )
        );
        
     // == UC8 ==

        // Convert TO base unit (feet)
        System.out.println("UC8: INCHES -> FEET = "
                + LengthUnit.INCHES.toFeet(12.0));

        System.out.println("UC8: YARDS -> FEET = "
                + LengthUnit.YARDS.toFeet(1.0));

        // Convert FROM base unit (feet)
        System.out.println("UC8: FEET -> INCHES = "
                + LengthUnit.INCHES.fromFeet(1.0));

        System.out.println("UC8: FEET -> CENTIMETERS = "
                + LengthUnit.CENTIMETERS.fromFeet(1.0));

        // QuantityLength delegates conversion to LengthUnit
        System.out.println(
            new QuantityLength(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES)
        );

        // Equality using delegated conversion
        System.out.println(
            new QuantityLength(36.0, LengthUnit.INCHES)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS))
        );
    }
}