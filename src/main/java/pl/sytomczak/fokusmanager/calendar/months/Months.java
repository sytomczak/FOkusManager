package pl.sytomczak.fokusmanager.calendar.months;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

public class Months {

    private ActualMonth actualMonth = new ActualMonth();
    int year = YearMonth.now().getYear();
    int month = YearMonth.now().getMonthValue();
    YearMonth thisMonth = YearMonth.now();


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

    public String getPreviousMonth() {
        YearMonth lastMonth = thisMonth.minusMonths(1);

        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);

        return lastMonth.format(monthYearFormatter);
    }

    public String getLaterMonth() {
        YearMonth laterMonth = thisMonth.plusMonths(1);

        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);

        return laterMonth.format(monthYearFormatter);
    }
}


