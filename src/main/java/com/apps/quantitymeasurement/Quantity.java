package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * Generic immutable Quantity class for all measurement categories.
 */
public final class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }
    
    public double getValue() {
        return value;
    }
    
    public U getUnit() {
        return unit;
    }


    /* ================= Conversion ================= */

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = toBase();
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(converted, targetUnit);
    }

    /* ================= Addition ================= */

    public Quantity<U> add(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        double sumBase = this.toBase() + other.toBase();
        return new Quantity<>(unit.convertFromBaseUnit(sumBase), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        double sumBase = this.toBase() + other.toBase();
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumBase), targetUnit);
    }

    /* ================= Equality ================= */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Cross-category prevention (Length ≠ Weight)
        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), toBase());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }

	
}