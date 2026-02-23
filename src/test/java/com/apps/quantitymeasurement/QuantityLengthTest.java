package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthTest {

    private static final double EPS = 1e-6;


  //     UC5: UNIT CONVERSION TESTS


    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPS);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS),
                EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                1e-4);
    }

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0,
                QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPS);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPS);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double value = 7.5;
        double inches = QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double feetBack = QuantityLength.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, feetBack, EPS);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    void testInstanceConvertTo() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength inches = q.convertTo(LengthUnit.INCHES);

        assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), inches);
    }


//       UC6: ADDITION (IMPLICIT TARGET UNIT)


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

        assertEquals(new QuantityLength(5.08, LengthUnit.CENTIMETERS), result);
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
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, LengthUnit.FEET).add(null));
    }


//       UC7: ADDITION WITH EXPLICIT TARGET UNIT


    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET);

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES);

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        assertEquals(new QuantityLength(2.0 / 3.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        QuantityLength r1 =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS);

        QuantityLength r2 =
                QuantityLength.add(
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        new QuantityLength(1.0, LengthUnit.FEET),
                        LengthUnit.YARDS);

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(1.0, LengthUnit.FEET),
                        null));
    }
}