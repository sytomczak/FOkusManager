package pl.sytomczak.fokusmanager.calendar.months;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.util.*;


public class Months {


    private ActualMonth actualMonth = new ActualMonth();

    private YearMonth yearMonthObject() {
        int year = YearMonth.now().getYear();
        int month = YearMonth.now().getMonthValue();

        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject;
    }

    public int daysInMonth() {
        int daysInMonth = yearMonthObject().lengthOfMonth();
        return daysInMonth;
    }

    public String firstDayOfMonth() {
        String firstDay = yearMonthObject().atDay(1).getDayOfWeek().name();
        return firstDay;
    }

    public DayOfWeek getDayOfMonth(Integer month)
    {
        Integer temp = month;
        temp = month <= 0 ? 1 : month;
        temp = temp > 12 ? 12 : temp;
        return yearMonthObject().atDay(temp).getDayOfWeek();
    }

    public String lastDayOfActualMonth() {
        String lastDay = yearMonthObject().atEndOfMonth().getDayOfWeek().name();
        return lastDay;
    }
}



