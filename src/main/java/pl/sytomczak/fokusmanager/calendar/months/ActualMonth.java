package pl.sytomczak.fokusmanager.calendar.months;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;


public class ActualMonth {

//    public String getActualMonth() {
//        Calendar cal = Calendar.getInstance();
//        return new SimpleDateFormat("MMMM").format(cal.getTime());
//
//    }

    public static void setActualMonth(JEditorPane monthPanel) {
        YearMonth thisMonth = YearMonth.now();
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH);
        monthPanel.setText(thisMonth.format(monthYearFormatter));
    }
}
