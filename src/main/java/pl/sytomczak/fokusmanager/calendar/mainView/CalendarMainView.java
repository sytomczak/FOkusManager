package pl.sytomczak.fokusmanager.calendar.mainView;

import pl.sytomczak.fokusmanager.calendar.Clock;
import pl.sytomczak.fokusmanager.calendar.months.ActualMonth;
import pl.sytomczak.fokusmanager.calendar.months.Months;
import pl.sytomczak.fokusmanager.calendar.years.ActualYear;
import pl.sytomczak.fokusmanager.dbutils.DBConnection;
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
    private JTextField legendTextField;
    private JEditorPane clockPanel;
    private JEditorPane monthPanel;
    private JRadioButton legendBottom;
    private JEditorPane yearsPanel;

    private JButton dayButton = new JButton();
    private Months months = new Months();
    private ActualYear actualYear = new ActualYear();


    public CalendarMainView() throws Exception {
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
                notesView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                notesView.setLocationRelativeTo(null);
            }
        });

        Clock.runClock(clockPanel);

        ActualMonth.setActualMonth(monthPanel);

        ActualYear.getActualYear(yearsPanel);

        controlsInActualMonth();
    }

    public void createControls() {
        int addSpace = 0;

        dayButton = new JButton();
        dayButton.setBackground(Color.yellow);
        calendarJPanel.setLayout(null);
     //   dayButton.setSize(80, 80);
        addSpace = addSpace + 40;
        calendarJPanel.add(dayButton);

    }
    private void controlsInActualMonth() {

        int addSpace = 0;

        for (int i = 0; i <= months.daysInMonth(); i++) {
            dayButton = new JButton();
            dayButton.setBackground(Color.yellow);
            calendarJPanel.setLayout(null);
            //   dayButton.setSize(80, 80);
            addSpace = addSpace + 40;
            calendarJPanel.add(dayButton);
            dayButton.setText(String.valueOf(i + 1));

            if (i <= 6) {
               // createControls();

                dayButton.setBounds(60 + addSpace , 50,80,80);

            } else if (i >= 6 && i <= 13) {
                //createControls();
                dayButton.setBounds(60 + addSpace, 150, 80,80);

            } else if (i > 13 && i <= 20) {
             //   createControls();
                dayButton.setBounds(60 + addSpace, 300, 80,80);

            } else if (i > 20) {
               // createControls();
                dayButton.setBounds(60 + addSpace, 450, 80, 80);



//            JButton dayButton = new JButton();
//            dayButton.setBackground(Color.yellow);
//            calendarJPanel.setLayout(null);
//            dayButton.setSize(80, 80);
//            dayButton.setText(String.valueOf(i + 1));
//            dayButton.setBounds(60 + addSpace , 60, 80, 80);
//            addSpace = addSpace + 40;
//            calendarJPanel.add(dayButton);
                //            i = i++;
            }
            i = i++;
        }
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

        System.out.println(calendarMainView.months.daysInMonth());
    }


}
