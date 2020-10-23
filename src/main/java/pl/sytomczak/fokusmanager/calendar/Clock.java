package pl.sytomczak.fokusmanager.calendar;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    public static void runClock(final JEditorPane clockJPanel) {
        Thread thread = new Thread() {

            public void run() {
                while (true) {
                    DateFormat calendar = new SimpleDateFormat("HH : mm : ss");

                    String data = calendar.format(new Date()).toString();
                    clockJPanel.setText(data);

                    try {
                        sleep(1);
                    } catch (Exception ex) {
                        ex.fillInStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
