package pl.sytomczak.fokusmanager.calender.months;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActualMonth {

    public static void actualMonth(JEditorPane monthPanel) {

        Calendar cal = Calendar.getInstance();
        monthPanel.setText(new SimpleDateFormat("MMMMMMMMMMM").format(cal.getTime()).toUpperCase());
    }


}