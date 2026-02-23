package com.apps.quantitymeasurement;


public enum LengthUnit {

    FEET(1.0),                 
    INCHES(1.0 / 12.0),          
    YARDS(3.0),                 
    CENTIMETERS(0.393701 / 12);  

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    //Converts a value in this unit to feet 
    public double toFeet(double value) {
        return value * toFeetFactor;
    }

    /** Converts a value in feet to this unit */
    public double fromFeet(double feetValue) {
        return feetValue / toFeetFactor;
    }
}