package pl.sytomczak.fokusmanager.calendar;

import pl.sytomczak.fokusmanager.calendar.months.ActualMonth;
import pl.sytomczak.fokusmanager.calendar.months.Months;
import pl.sytomczak.fokusmanager.calendar.years.ActualYear;
import pl.sytomczak.fokusmanager.calendar.years.Years;
import pl.sytomczak.fokusmanager.dbutils.DBConnection;
import pl.sytomczak.fokusmanager.notes.daynotesview.DayNotesView;
import pl.sytomczak.fokusmanager.notes.notesview.NotesView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;


public class CalendarMainView extends JFrame {
    private JPanel mainJPanel;
    private JPanel calendarJPanel;
    private JPanel notesJPanel;
    private JTextField notesTitle;
    private JTextField calendarTitle;
    private JRadioButton yearsRadioButton;
    private JRadioButton newNotesRadioButton;
    private JPanel legendJPanel;
    private JTextField legendTitle;
    private JEditorPane clockPanel;
    private JEditorPane monthPanel;
    private JRadioButton legendBottom;
    private JEditorPane yearsPanel;

    public JButton dayButton = new JButton();
    private Months months = new Months();
    private ActualYear actualYear = new ActualYear();
    public String DayName;


    public CalendarMainView() {
        setContentPane(mainJPanel);
        setTitle("FocusManager");

        mainJPanel.setPreferredSize(new Dimension(1400, 900));
        mainJPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        newNotesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesView notesView = new NotesView();
                notesView.pack();
                notesView.setResizable(false);
                notesView.setVisible(true);
                notesView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                notesView.setLocationRelativeTo(null);
            }
        });

        yearsRadioButton.setSize(20,20);
        yearsRadioButton.setLocation(1080, 580);

        legendBottom.setSize(20,20);
        legendBottom.setLocation(280, 980);

        newNotesRadioButton.setSize(20,20);
        newNotesRadioButton.setLocation(1080, 0);

        calendarTitle.setSize(65,25);
        calendarTitle.setLocation(0,0);

        legendTitle.setSize(60,30);
        legendTitle.setLocation(0,0);

        Clock.runClock(clockPanel);
        clockPanel.setSize(100,30);
        clockPanel.setLocation(0,570);

        ActualMonth.setActualMonth(monthPanel);
        monthPanel.setSize(130, 30);
        monthPanel.setLocation(960,0);

        ActualYear.getActualYear(yearsPanel);
        yearsPanel.setSize(50,30);
        yearsPanel.setLocation(1000,570);

        controlsInActualMonth();

    }

    private void createControls() {
        dayButton = new JButton();
        dayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayNotesView dayNotesView = new DayNotesView(DayName);
                dayNotesView.pack();
                dayNotesView.setResizable(false);
                dayNotesView.setVisible(true);
                dayNotesView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dayNotesView.setLocationRelativeTo(null);
            }
        });
        dayButton.setBackground(Color.yellow);
        calendarJPanel.setLayout(null);
        dayButton.setSize(80, 80);
        calendarJPanel.add(dayButton);
    }

    public int controlsInActualMonth() {

        int numberOfDaysEarlierInFirstMonday = months.numberOfDaysToTheFirstMondayInActualMonth() - 1;
        int addSpace = 0;
        int addSpace1 = 0;
        int addSpace2 = 0;
        int addSpace3 = 0;
        int addSpace4 = 0;
        int addSpace5 = 0;

        for (int i = 0; i <= months.daysInMonth() - 1; i++) {

            createControls();
            dayButton.setText(String.valueOf(i + 1));
            dayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DayName = ((JButton)e.getSource()).getText();
                }
            });

            if (i < numberOfDaysEarlierInFirstMonday) {
                dayButton.setLocation(170 + (880 - (months.numberOfDaysToTheFirstMondayInActualMonth() * 110)) + addSpace, 30);
                addSpace = addSpace + 110;

            } else if (i <= 6 + numberOfDaysEarlierInFirstMonday) {
                dayButton.setLocation(170 + addSpace1, 120);
                addSpace1 = addSpace1 + 110;

            } else if (i >= 6 + numberOfDaysEarlierInFirstMonday && i <= 13 + numberOfDaysEarlierInFirstMonday) {
                dayButton.setLocation(170 + addSpace2, 210);
                addSpace2 = addSpace2 + 110;

            } else if (i > 13 + numberOfDaysEarlierInFirstMonday && i <= 20 + numberOfDaysEarlierInFirstMonday) {
                dayButton.setLocation(170 + addSpace3, 300);
                addSpace3 = addSpace3 + 110;

            } else if (i > 20 + numberOfDaysEarlierInFirstMonday && i <= 27 + numberOfDaysEarlierInFirstMonday) {
                dayButton.setLocation(170 + addSpace4, 390);
                addSpace4 = addSpace4 + 110;

            } else if (i > 27 + numberOfDaysEarlierInFirstMonday && i <= months.daysInMonth() - 1) {
                dayButton.setLocation(170 + addSpace5, 480);
                addSpace5 = addSpace5 + 110;
            }

            i = i++;
        }
        return months.daysInMonth();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) throws Exception {
        CalendarMainView calendarMainView = new CalendarMainView();
        calendarMainView.pack();
        calendarMainView.setResizable(false);
        calendarMainView.setVisible(true);
        calendarMainView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        calendarMainView.setLocationRelativeTo(null);

        Connection connection = DBConnection.getConnection();

        Months months = new Months();
        System.out.println(months.getPreviousMonth());
        System.out.println(months.getLaterMonth());

        Years year = new Years();
        System.out.println(year.getLaterYear2());
        System.out.println(year.getPreviousYear2());



    }

}

