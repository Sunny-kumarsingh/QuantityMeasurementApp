package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityLengthTest {

    // ================= UC1 =================
    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.INCH)
        );
    }

    // ================= UC2 =================
    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        assertNotEquals(
                new QuantityLength(1.0, LengthUnit.INCH),
                new QuantityLength(2.0, LengthUnit.INCH)
        );
    }

    // ================= UC3 =================
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertEquals(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    // ================= UC4 =================
    @Test
    void testEquality_YardToFeet() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_YardToInches() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(36.0, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_CentimeterToInch() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.CENTIMETERS),
                new QuantityLength(0.393701, LengthUnit.INCH)
        );
    }

    @Test
    void testEquality_TransitiveProperty() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);

        assertEquals(yard, feet);
        assertEquals(feet, inch);
        assertEquals(yard, inch);
    }

    // ================= Equals Contract =================
    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(q, null);
    }

    @Test
    void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testHashCode_Consistency() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(36.0, LengthUnit.INCH);

        assertEquals(q1, q2);
        assertEquals(q1.hashCode(), q2.hashCode());
    }
}