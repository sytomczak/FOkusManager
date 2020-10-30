package pl.sytomczak.fokusmanager.calendar.months;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Months {

    private ActualMonth actualMonth;

        public int daysInMonth() {
            actualMonth = new ActualMonth();
            Map<String, Integer> daysInMonths = new HashMap<>();
//        daysInMonths.put("January", 31);
//        daysInMonths.put("February", 28);
//        daysInMonths.put("March", 31);
//        daysInMonths.put("April", 30);
//        daysInMonths.put("May", 31);
//        daysInMonths.put("June", 30);
//        daysInMonths.put("July", 31);
//        daysInMonths.put("August", 31);
//        daysInMonths.put("September", 30);
//        daysInMonths.put("październik", 31);
//        daysInMonths.put("November", 30);
//        daysInMonths.put("December", 31);
            DateFormatSymbols dfs = new DateFormatSymbols();
            String[] months = dfs.getMonths();
            for (int i = 0; i < months.length; i++) {
                Integer days = Days(i+1, 2020);
                daysInMonths.put(months[i], days);
            }


            int value = 0;

            for (String key : daysInMonths.keySet()) {
                if (key.contains(actualMonth.getActualMonth())) {
                    value = daysInMonths.get(key);

                }
            }
            return value;

        }


    private int Days(int month, int year)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }
}


