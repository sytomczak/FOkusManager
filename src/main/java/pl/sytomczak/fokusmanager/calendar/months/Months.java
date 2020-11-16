package pl.sytomczak.fokusmanager.calendar.months;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class Months {


    private ActualMonth actualMonth = new ActualMonth();
    int year = YearMonth.now().getYear();
    int month = YearMonth.now().getMonthValue();

    private YearMonth yearMonthObject() {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject;
    }

    public int daysInMonth() {
        int daysInMonth = yearMonthObject().lengthOfMonth();
        return daysInMonth;
    }

    public int numberOfDaysToTheFirstMondayInActualMonth() {
        LocalDate now = LocalDate.now();
        LocalDate firstMonday = now.with(firstInMonth(DayOfWeek.MONDAY));

        int days = 0;
        for (int i = 1; i <= daysInMonth(); i++) {
            LocalDate date = LocalDate.of(year, month, i);
            days++;
            if (date.equals(firstMonday)) {
                return days;
            }
        }
        return days;
    }
}


