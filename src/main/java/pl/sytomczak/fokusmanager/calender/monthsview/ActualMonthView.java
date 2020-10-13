package pl.sytomczak.fokusmanager.calender.monthsview;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ActualMonthView {

    public static void actualMonth(JEditorPane monthPanel) {

        Calendar cal = Calendar.getInstance();
        monthPanel.setText(new SimpleDateFormat("MMMMMMMMMMM").format(cal.getTime()).toUpperCase());

    }



}