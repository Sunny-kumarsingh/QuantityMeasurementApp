package com.apps.quantitymeasurement;

import java.util.Objects;


public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    private static void validate(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    private double toBaseFeet() {
        return unit.toFeet(value);
    }



    /** Static conversion API */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value, source);
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double feet = source.toFeet(value);
        return target.fromFeet(feet);
    }

    /** Instance conversion → returns NEW QuantityLength */
    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double converted = convert(this.value, this.unit, target);
        return new QuantityLength(converted, target);
    }

    /* ================= Equality (UC3/UC4) ================= */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBaseFeet(), other.toBaseFeet()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseFeet());
    }

    @Override
    public String toString() {
        return value + " " + unit.name();
    }
}