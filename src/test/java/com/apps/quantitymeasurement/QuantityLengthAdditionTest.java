package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthAdditionTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result =
            new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(2.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result =
            new QuantityLength(6.0, LengthUnit.INCHES)
                .add(new QuantityLength(6.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        QuantityLength result =
            new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength result =
            new QuantityLength(12.0, LengthUnit.INCHES)
                .add(new QuantityLength(1.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength result =
            new QuantityLength(1.0, LengthUnit.YARDS)
                .add(new QuantityLength(3.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_CentimeterPlusInch() {
        QuantityLength result =
            new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .add(new QuantityLength(1.0, LengthUnit.INCHES));


        QuantityLength expected =
            new QuantityLength(5.08, LengthUnit.CENTIMETERS);

        assertEquals(expected, result);
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength result =
            new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength result =
            new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NullSecondOperand() {
        assertThrows(IllegalArgumentException.class, () ->
            new QuantityLength(1.0, LengthUnit.FEET).add(null)
        );
    }

    @Test
    void testAddition_LargeValues() {
        QuantityLength result =
            new QuantityLength(1e6, LengthUnit.FEET)
                .add(new QuantityLength(1e6, LengthUnit.FEET));

        assertEquals(new QuantityLength(2e6, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SmallValues() {
        QuantityLength result =
            new QuantityLength(0.001, LengthUnit.FEET)
                .add(new QuantityLength(0.002, LengthUnit.FEET));

        assertEquals(new QuantityLength(0.003, LengthUnit.FEET), result);
    }
}