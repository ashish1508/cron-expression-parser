package org.deliveroo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {

    @Test
    void testMinuteLimits () {
        assertEquals(59, org.deliveroo.enums.Field.MINUTE.maxValue);
        assertEquals(0, org.deliveroo.enums.Field.MINUTE.minValue);
    }

    @Test
    void testHourLimits () {
        assertEquals(23, org.deliveroo.enums.Field.HOUR.maxValue);
        assertEquals(0, org.deliveroo.enums.Field.HOUR.minValue);
    }

    @Test
    void testDayOfMonthLimits () {
        assertEquals(31, org.deliveroo.enums.Field.DAY_OF_MONTH.maxValue);
        assertEquals(1, org.deliveroo.enums.Field.DAY_OF_MONTH.minValue);
    }

    @Test
    void testMonthLimits () {
        assertEquals(12, org.deliveroo.enums.Field.MONTH.maxValue);
        assertEquals(1, org.deliveroo.enums.Field.MONTH.minValue);
    }

    @Test
    void testDayOfWeekLimits () {
        assertEquals(6, org.deliveroo.enums.Field.DAY_OF_WEEK.maxValue);
        assertEquals(0, org.deliveroo.enums.Field.DAY_OF_WEEK.minValue);
    }

}
