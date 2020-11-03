package pl.sytomczak.fokusmanager.calendar.months;

import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Months {


    private ActualMonth actualMonth = new ActualMonth();

    public int daysInMonth() {
//        Map<String, Integer> daysInMonths = new HashMap<>();
//
//        DateFormatSymbols dfs = new DateFormatSymbols();
//        String[] months = dfs.getMonths();
//
//        for (int i = 0; i < months.length; i++) {
//            Integer days = Days(i + 1, 2020);
//            daysInMonths.put(months[i], days);
//
//        }
//
//
//
//        int value = 0;
//
//        for (String key : daysInMonths.keySet()) {
//            if (key.contains(actualMonth.getActualMonth())) {
//                value = daysInMonths.get(key);
//            }
//        }
//        return value;

        int year = YearMonth.now().getYear();
        int month = YearMonth.now().getMonthValue();

        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }

    public int Days(int month, int year) {  //private było,  tutaj zle przipsuje dni do miesiaca
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);

        return calendar.getActualMaximum(Calendar.DATE);
    }
}



