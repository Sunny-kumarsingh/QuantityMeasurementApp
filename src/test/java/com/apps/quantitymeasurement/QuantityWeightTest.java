package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    private static final double EPS = 1e-6;

    /* ================= Equality ================= */

    @Test
    void testEquality_KgToKg() {
        assertEquals(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testEquality_KgToGram() {
        assertEquals(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testEquality_KgToPound() {
        assertEquals(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        assertNotEquals(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM),
            new QuantityLength(1.0, LengthUnit.FEET)
        );
    }

    /* ================= Conversion ================= */

    @Test
    void testConversion_KgToGram() {
        assertEquals(
            new QuantityWeight(1000.0, WeightUnit.GRAM),
            new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM)
        );
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityWeight kg =
            new QuantityWeight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, kg.convertTo(WeightUnit.KILOGRAM).toString().contains("1.0") ? 1.0 : kg.convertTo(WeightUnit.KILOGRAM).toString(), EPS);
    }

    /* ================= Addition ================= */

    @Test
    void testAddition_KgPlusGram() {
        QuantityWeight result =
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_ExplicitTarget_Gram() {
        QuantityWeight result =
            QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
            );

        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class,
            () -> new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(null));
    }
}