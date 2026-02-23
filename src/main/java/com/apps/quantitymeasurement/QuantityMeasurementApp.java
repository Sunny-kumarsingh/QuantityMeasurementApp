package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES))
        );

        System.out.println(
            QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET))
        );
    }
}