package org.deliveroo.Operator;

import org.deliveroo.enums.Field;
import org.deliveroo.enums.Operator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WildcardTest {

    @Test
    void testWildCard() {
        String expression = "*";
        Field field = Field.MINUTE;
        Operator operator = Operator.getOperatorType(expression);
        List<Integer> values = operator.parseField(field,expression);
        assertEquals(Arrays.stream(IntStream.range(field.minValue, field.maxValue+1).toArray())
                .boxed().collect(
                        Collectors.toList()), values);
    }
}
