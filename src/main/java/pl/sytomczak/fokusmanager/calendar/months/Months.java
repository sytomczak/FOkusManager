package pl.sytomczak.fokusmanager.calendar.months;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Months {

    private int year;
    private int month;
    private ActualMonth actualMonth;


    //  private Map<String, Integer> daysInMonths(String months, Integer days){
    private int daysInMonth() {
        Map<String, Integer> daysInMonths = new HashMap<>();
        daysInMonths.put("January", 31);
        daysInMonths.put("February", 28);
        daysInMonths.put("March", 31);
        daysInMonths.put("April", 30);
        daysInMonths.put("May", 31);
        daysInMonths.put("June", 30);
        daysInMonths.put("July", 31);
        daysInMonths.put("August", 31);
        daysInMonths.put("September", 30);
        daysInMonths.put("October", 31);
        daysInMonths.put("November", 30);
        daysInMonths.put("December", 31);

        int value = 0;

        for (String key : daysInMonths.keySet()) {
            if (key.contains(actualMonth.getActualMonth())) {
                value = daysInMonths.get(key);

            }
        }
        return value;
    }

    public int daysInActualMonth() {
        daysInMonth();
    }

    private static int daysInMonth(GregorianCalendar c) {
        final int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
        return daysInMonths[c.get(GregorianCalendar.MONTH)];
    }
}
