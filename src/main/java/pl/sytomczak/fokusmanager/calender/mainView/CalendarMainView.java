package pl.sytomczak.fokusmanager.calender.mainView;

import pl.sytomczak.fokusmanager.calender.monthsview.ActualMonthView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CalendarMainView extends JFrame {
    private JPanel mainJPanel;
    private JPanel legendJPanel;
    private JPanel calendarJPanel;
    private JPanel notesJPanel;
    private JTextField notesTitle;
    private JTextField legendTitle;
    private JTextField calendarTitle;
    private JRadioButton yearsRadioButton;
    private JRadioButton newNotesRadioButton;
    private JRadioButton newCategoryRadioButton;
    private JPanel clockJPane;
    private JPanel actualNameMonthPanel;

    public CalendarMainView() {
        setContentPane(mainJPanel);
        setTitle("FocusManager");

        mainJPanel.setPreferredSize(new Dimension(1400, 800));
        mainJPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ActualMonthView actualMonthView  = new ActualMonthView();
        actualMonthView.getContentPane().add(calendarJPanel);
     //   calendarJPanel.add(mainJPanel);
        //mainJPanel.add(calendarJPanel);


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
