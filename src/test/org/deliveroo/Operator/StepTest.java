package org.deliveroo.Operator;

import org.deliveroo.enums.Field;
import org.deliveroo.enums.Operator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StepTest {

    @Test
    void testStep() {
        String expression = "*/15";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field,expression);
        int minuteEnd = 59;
        int minuteStart = 0;
        List<Integer> expectedList = IntStream.iterate(minuteStart, i -> i + 15)
                .limit((minuteEnd - minuteStart) / 15 + 1)
                .boxed()
                .collect(Collectors.toList());
        assertEquals(expectedList, values);

    }

    @Test
    void testIncompleteStep() {
        String expression = "2/";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals(exception.getMessage(), "Illegal step expression : "+ expression);

    }

    @Test
    void testStepStartInvalid() {
        String expression = "35/2";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Step start is invalid, it should lie between 1 and 31", exception.getMessage());

    }

    @Test
    void testStepValueInvalid() {
        String expression = "12/45";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Step value is invalid, it should lie between 1 and 31", exception.getMessage());

    }
}
