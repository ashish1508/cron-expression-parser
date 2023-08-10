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

public class RangeTest {

    @Test
    void testRange() {
        String expression = "2-5";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field,expression);
        assertEquals(Arrays.stream(IntStream.range(2, 6).toArray())
                .boxed().collect(
                        Collectors.toList()), values);

    }

    @Test
    void testIncompleteRange() {
        String expression = "2-";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Illegal range expression", exception.getMessage());

    }

    @Test
    void testRangeMinInvalid() {
        String expression = "0-29";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Range minimum is invalid, it should lie between 1 and 31", exception.getMessage());

    }

    @Test
    void testRangeMaxInvalid() {
        String expression = "1-40";
        Field field = Field.DAY_OF_MONTH;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Range maximum is invalid, it should lie between 1 and 31", exception.getMessage());

    }
}
