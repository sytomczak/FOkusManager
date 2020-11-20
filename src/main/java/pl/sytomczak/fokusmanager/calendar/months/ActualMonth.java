package pl.sytomczak.fokusmanager.calendar.months;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActualMonth {

    public String getActualMonth() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("MMMMM").format(cal.getTime());

    }

    public static void setActualMonth(JEditorPane monthPanel) {
        ActualMonth choseGetActualMonthMethod = new ActualMonth();
        monthPanel.setText(choseGetActualMonthMethod.getActualMonth().toUpperCase());
    }
}