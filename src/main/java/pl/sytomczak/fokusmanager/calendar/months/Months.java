package pl.sytomczak.fokusmanager.calendar.months;

import java.time.DayOfWeek;
import java.time.YearMonth;

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
        public String lastDayOfActualMonth() {
            String lastDay = yearMonthObject().atEndOfMonth().getDayOfWeek().name();
            return lastDay;
        }
    }


