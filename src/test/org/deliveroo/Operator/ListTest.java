package org.deliveroo.Operator;

import org.deliveroo.enums.Field;
import org.deliveroo.enums.Operator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListTest {

    @Test
    void testList () {
        String expression = "2,5,7,8";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field, expression);
        assertEquals(Arrays.asList(2, 5, 7, 8), values);

    }

    @Test
    void testListWithInvalidValues () {
        String expression = "1,23,60";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field, expression);
        });
        assertEquals("Value is invalid, it should lie between 1 and 31", exception.getMessage());

    }

    @Test
    void testListWithRangeValues () {
        String expression = "1-5,7-10";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field, expression);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10), values);

    }

    @Test
    void testListWithOverlappingRangeValues () {
        String expression = "1-5,2-7";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field, expression);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), values);

    }
}
