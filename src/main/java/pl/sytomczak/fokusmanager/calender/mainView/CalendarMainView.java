package pl.sytomczak.fokusmanager.calender.mainView;

import pl.sytomczak.fokusmanager.clock.Clock;
import pl.sytomczak.fokusmanager.calender.months.ActualMonth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CalendarMainView extends JFrame {
    private JPanel mainJPanel;
    private JPanel calendarJPanel;
    private JPanel notesJPanel;
    private JTextField notesTitle;
    private JTextField calendarTitle;
    private JRadioButton yearsRadioButton;
    private JRadioButton newNotesRadioButton;
    private JPanel legendJPanel;
    private JTextField legendTextField;
    private JEditorPane clockPanel;
    private JEditorPane monthPanel;
    private JRadioButton lehendBottom;

    public CalendarMainView() {
        setContentPane(mainJPanel);
        setTitle("FocusManager");

        mainJPanel.setPreferredSize(new Dimension(1400, 900));
        mainJPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        Clock.runClock(clockPanel);
        ActualMonth.actualMonth(monthPanel);
    }


    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        CalendarMainView calendarMainView = new CalendarMainView();
        calendarMainView.pack();
        calendarMainView.setResizable(false);
        calendarMainView.setVisible(true);
        calendarMainView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        calendarMainView.setLocationRelativeTo(null);
    }
}
