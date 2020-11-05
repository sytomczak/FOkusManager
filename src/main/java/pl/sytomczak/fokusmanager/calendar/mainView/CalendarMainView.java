package pl.sytomczak.fokusmanager.calendar.mainView;

import com.mindfusion.drawing.Colors;
import org.joda.time.Days;
import pl.sytomczak.fokusmanager.calendar.Clock;
import pl.sytomczak.fokusmanager.calendar.months.ActualMonth;
import pl.sytomczak.fokusmanager.calendar.months.ActualYear;
import pl.sytomczak.fokusmanager.calendar.months.Months;
import pl.sytomczak.fokusmanager.dbutils.DBConnection;
import pl.sytomczak.fokusmanager.notes.notesview.NotesView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

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

        months.daysInMonth();

        ActualYear.getActualYear(yearsPanel);

        controlsInActualMonth();

//        JButton dayButton = new JButton();
//        dayButton.setBackground(Color.yellow);
//        calendarJPanel.setLayout(null);
//     //   dayButton.setSize(80, 80);
//        dayButton.setText(String.valueOf(1));
//        dayButton.setBounds(623, 113, 80, 80);
//        calendarJPanel.add(dayButton);

    }


    private void onCancel() {
        dispose();
    }

    private void controlsInActualMonth() throws Exception {


        for (int i = 0; i <= months.daysInMonth(); i++) {
        JButton dayButton = new JButton();
        dayButton.setBackground(Color.yellow);
        calendarJPanel.setLayout(null);
          dayButton.setSize(80, 80);
        dayButton.setText(String.valueOf(i +1));
        dayButton.setBounds(623 + i+70 , 113 + i+70, 80, 80);
        calendarJPanel.add(dayButton);
        i = i++;
        }
//
//        int i = 0;

//        do {
//            try {
//                JButton dayButton = new JButton();
//                dayButton.setBackground(Color.yellow);
//                dayButton.setSize(10, 10);
//                //dayButton.setLayout(null);
//                dayButton.setName(String.valueOf(i));
//                JLabel lb = new JLabel();
//                lb.setText("asdasdasd");
//                if(calendarJPanel != null)
//                    calendarJPanel.add(lb); //dayButton);
//                i++;
//            } catch (Exception ex) {
//                String s = "";
//            }
//        }
//        while (i >= 3);
    }


            public static void main (String[]args) throws Exception {
                CalendarMainView calendarMainView = new CalendarMainView();
                calendarMainView.pack();
                calendarMainView.setResizable(false);
                calendarMainView.setVisible(true);
                calendarMainView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                calendarMainView.setLocationRelativeTo(null);

                Connection connection = DBConnection.getConnection();

//                int year = YearMonth.now().getYear();
//                int month = YearMonth.now().getMonthValue();
//
//
//                YearMonth yearMonthObject = YearMonth.of(year, month);
//                int daysInMonth = yearMonthObject.lengthOfMonth();
                System.out.println(calendarMainView.months.daysInMonth());
            }


        }
