package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * Immutable value object representing a weight measurement.
 */
public final class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        validate(value, unit);
        this.value = value;
        this.unit = unit;
    }

    /* ================= Validation ================= */

    private static void validate(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    private double toBaseKg() {
        return unit.toKilogram(value);
    }

    /* ================= Conversion ================= */

    public QuantityWeight convertTo(WeightUnit target) {
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double kg = toBaseKg();
        return new QuantityWeight(target.fromKilogram(kg), target);
    }

    /* ================= Addition (implicit target) ================= */

    public QuantityWeight add(QuantityWeight other) {
        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }
        double sumKg = this.toBaseKg() + other.toBaseKg();
        return new QuantityWeight(unit.fromKilogram(sumKg), this.unit);
    }

    /* ================= Addition (explicit target) ================= */

    public static QuantityWeight add(QuantityWeight a,
                                     QuantityWeight b,
                                     WeightUnit targetUnit) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumKg = a.toBaseKg() + b.toBaseKg();
        return new QuantityWeight(targetUnit.fromKilogram(sumKg), targetUnit);
    }

    /* ================= Equality ================= */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;
        return Double.compare(this.toBaseKg(), other.toBaseKg()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseKg());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}