package org.deliveroo.enums;

import org.deliveroo.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum Operator {
    WILDCARD() {
        @Override
        public List<Integer> parseField (Field field, String expression) {
            return Arrays.stream(IntStream.range(field.minValue, field.maxValue + 1)
                                          .toArray())
                         .boxed()
                         .collect(Collectors.toList());
        }
    }, RANGE() {
        @Override
        public List<Integer> parseField (Field field, String expression) {

            List<Integer> range = Stream.of(expression.split("-"))
                                        .map(Integer::valueOf)
                                        .collect(Collectors.toList());
            if (range.size() != 2) throw new IllegalArgumentException("Illegal range expression");
            Utils.isValid(field, range.get(0), "Range minimum");
            Utils.isValid(field, range.get(1), "Range maximum");
            return Arrays.stream(IntStream.range(range.get(0), range.get(1) + 1)
                                          .toArray())
                         .boxed()
                         .collect(Collectors.toList());
        }
    }, STEP() {
        @Override
        public List<Integer> parseField (Field field, String expression) {

            List<String> stepData = Arrays.stream(expression.split("/"))
                                          .collect(Collectors.toList());
            if (stepData.size() != 2) throw new IllegalArgumentException("Illegal step expression : " + expression);
            if (stepData.get(0).equals("*")) {
                stepData.set(0, field.minValue.toString());
            }
            List<Integer> step = Arrays.asList(Integer.valueOf(stepData.get(0)), Integer.valueOf(stepData.get(1)));
            Utils.isValid(field, step.get(0), "Step start");
            Utils.isValid(field, step.get(1), "Step value");

            return IntStream.iterate(step.get(0), n -> n + step.get(1))
                            .limit((field.maxValue - step.get(0)) / step.get(1) + 1)
                            .boxed()
                            .collect(Collectors.toList());
        }
    }, LIST() {
        @Override
        public List<Integer> parseField (Field field, String expression) {
            String[] exps = expression.split(",");
            return Stream.of(exps)
                         .flatMap(exp -> Operator.getOperatorType(exp)
                                                 .parseField(field, exp)
                                                 .stream())
                         .distinct()
                         .sorted()
                         .collect(Collectors.toList());

        }
    },

    VALUE() {
        @Override
        public List<Integer> parseField (Field field, String expression) {
            Integer value = Integer.valueOf(expression);
            Utils.isValid(field, value, "Value");
            return Collections.singletonList(value);
        }
    };

    public abstract List<Integer> parseField (Field field, String expression);

    public static Operator getOperatorType (String expression) {
        if (expression.equals("*")) return Operator.WILDCARD;
        if (expression.contains(",")) return Operator.LIST;
        if (expression.contains("-")) return Operator.RANGE;
        if (expression.contains("/")) return Operator.STEP;
        return Operator.VALUE;
    }

}
