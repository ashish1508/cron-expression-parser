package org.deliveroo.Operator;

import org.deliveroo.enums.Field;
import org.deliveroo.enums.Operator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValueTest {

    @Test
    void testValue() {
        String expression = "2";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field,expression);
        assertEquals(Collections.singletonList(2), values);

    }

    @Test
    void testInvalidValue() {
        String expression = "60";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            operator.parseField(field,expression);
        });
        assertEquals("Value is invalid, it should lie between 0 and 59", exception.getMessage());

    }
}
