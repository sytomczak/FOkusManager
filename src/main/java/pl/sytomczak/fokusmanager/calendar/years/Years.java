package pl.sytomczak.fokusmanager.calendar.years;

import javax.swing.*;
import java.time.YearMonth;

public class Years {

    public static void getPreviousYear(JEditorPane previousYear) {
        int year = YearMonth.now().getYear() - 1;
        previousYear.setText(Integer.toString(year));
    }

    public static void getLaterYear(JEditorPane laterYear) {
        int year = YearMonth.now().getYear() + 1;
        laterYear.setText(Integer.toString(year));
    }

    public int getPreviousYear2() {
        int year = YearMonth.now().getYear() - 1;
        return year;
    }

    public int getLaterYear2() {
        int year = YearMonth.now().getYear() + 1;
        return year;
    }
}
