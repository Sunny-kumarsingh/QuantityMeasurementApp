package com.app.quantitymeasurement.unit;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {

        @Override
        public double convertToBaseUnit(double value) {
            return value + 273.15;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue - 273.15;
        }

        @Override
        public String getUnitName() {
            return "Celsius";
        }

        @Override
        public boolean supportsAddition() { return false; }

        @Override
        public boolean supportsSubtraction() { return false; }

        @Override
        public boolean supportsDivision() { return false; }

        @Override
        public boolean supportsMultiplication() { return false; }
    },

    FAHRENHEIT {

        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32) * 5 / 9 + 273.15;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return (baseValue - 273.15) * 9 / 5 + 32;
        }

        @Override
        public String getUnitName() {
            return "Fahrenheit";
        }

        @Override
        public boolean supportsAddition() { return false; }

        @Override
        public boolean supportsSubtraction() { return false; }

        @Override
        public boolean supportsDivision() { return false; }

        @Override
        public boolean supportsMultiplication() { return false; }
    },

    KELVIN {

        @Override
        public double convertToBaseUnit(double value) {
            return value;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }

        @Override
        public String getUnitName() {
            return "Kelvin";
        }

        @Override
        public boolean supportsAddition() { return false; }

        @Override
        public boolean supportsSubtraction() { return false; }

        @Override
        public boolean supportsDivision() { return false; }

        @Override
        public boolean supportsMultiplication() { return false; }
    };

    @Override
    public MeasurementType getMeasurementType() {
        return MeasurementType.TEMPERATURE;
    }
}