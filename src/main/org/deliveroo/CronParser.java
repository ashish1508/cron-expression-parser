package org.deliveroo;

import org.deliveroo.enums.Field;
import org.deliveroo.enums.Operator;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class CronParser {

    private List<Integer> minute;
    private List<Integer> hour;
    private List<Integer> dayOfMonth;
    private List<Integer> month;
    private List<Integer> dayOfWeek;
    private String command;
    private final String inputExpression;

    public CronParser (String expression) {
        this.inputExpression = expression;
    }

    public static void main (String[] args) {

        CronParser cronParser = new CronParser(args[0]);
        cronParser.parse();
        System.out.println(cronParser);

    }

    public void parse () {

        String[] fields = inputExpression.split("\\s+");

        if (fields.length != 6) {
            throw new IllegalArgumentException(
                    "Expression should have 6 arguments, given " + fields.length + " arguments");
        }

        Operator minuteOperator = Operator.getOperatorType(fields[0]);
        minute = minuteOperator.parseField(Field.MINUTE, fields[0]);

        Operator hourOperator = Operator.getOperatorType(fields[1]);
        hour = hourOperator.parseField(Field.HOUR, fields[1]);

        Operator dayOfMonthOperator = Operator.getOperatorType(fields[2]);
        dayOfMonth = dayOfMonthOperator.parseField(Field.DAY_OF_MONTH, fields[2]);

        Operator monthOperator = Operator.getOperatorType(fields[3]);
        month = monthOperator.parseField(Field.MONTH, fields[3]);

        Operator dayOfWeekOperator = Operator.getOperatorType(fields[4]);
        dayOfWeek = dayOfWeekOperator.parseField(Field.DAY_OF_WEEK, fields[4]);

        command = fields[5];

    }


    public String toString () {
        return format("%-14s%s%n", "minute", display(minute)) +
                format("%-14s%s%n", "hour", display(hour)) +
                format("%-14s%s%n", "day of month", display(dayOfMonth)) +
                format("%-14s%s%n", "month", display(month)) +
                format("%-14s%s%n", "day of week", display(dayOfWeek)) +
                format("%-14s%s%n", "command", command);
    }

    public String display (List<Integer> values) {
        return values.stream()
                     .map(String::valueOf)
                     .collect(Collectors.joining(" "));
    }
}

