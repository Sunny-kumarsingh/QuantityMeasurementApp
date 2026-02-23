package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthAdditionTargetTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result =
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET
            );

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength result =
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES
            );

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength result =
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
            );

//        assertEquals(0.6666667,
//                QuantityLength.convert(result.convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET).convertTo(LengthUnit.FEET),
//                EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength r1 =
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
            );

        QuantityLength r2 =
            QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET),
                LengthUnit.YARDS
            );

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {
        assertThrows(IllegalArgumentException.class, () ->
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.FEET),
                null
            )
        );
    }
}