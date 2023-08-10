package org.deliveroo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CronParserTest {

    @Test
    void CronTest () {
        String expression = "*/15 0 1,15 * 1-5 /usr/bin/find";
        CronParser cronParser = new CronParser(expression);
        cronParser.parse();
        assertEquals("minute        0 15 30 45\n" +
                             "hour          0\n" +
                             "day of month  1 15\n" +
                             "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                             "day of week   1 2 3 4 5\n" +
                             "command       /usr/bin/find\n", cronParser.toString());
    }


    @Test
    void CronTestInvalidFieldsInExpression () {
        String expression = "2/15 1-5 1,2 2 * ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronParser cronParser = new CronParser(expression);
            cronParser.parse();
        });
        assertEquals("Expression should have 6 arguments, given 5 arguments", exception.getMessage());
    }

    @Test
    void CronTestInvalidExpression () {
        String expression = "2/15 30 1,3 2 * /usr/bin/find";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronParser cronParser = new CronParser(expression);
            cronParser.parse();
        });
        assertEquals("Value is invalid, it should lie between 0 and 23", exception.getMessage());
    }
}
