package pl.sytomczak.fokusmanager.calendar.years;

import javax.swing.*;
import java.time.YearMonth;

public class ActualYear {

    public static void getActualYear(JEditorPane yearsPanel) {
        int year = YearMonth.now().getYear();
        yearsPanel.setText(Integer.toString(year));
    }
}
