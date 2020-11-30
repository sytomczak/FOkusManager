package pl.sytomczak.fokusmanager.calendar;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class Date {

    public static void date(final JEditorPane dateEditorPane) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd / MM / yyyy");
        java.util.Date date = new java.util.Date();
        dateEditorPane.setText(formatter.format(date));
    }
}
