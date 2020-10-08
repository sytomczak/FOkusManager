package pl.sytomczak.fokusmanager.calender.mainView;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.CustomDrawElements;
import com.mindfusion.scheduling.awt.AwtCalendar;
import org.w3c.dom.Text;
import pl.sytomczak.fokusmanager.calender.monthsview.ActualMonthView;

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
    private JPanel clockJPane;
    private JPanel actualNameMonthPanel;
    private JPanel legendJPanel;
    private JTextField legendTextField;

    public CalendarMainView() {
        setContentPane(mainJPanel);
        setTitle("FocusManager");

        mainJPanel.setPreferredSize(new Dimension(1400, 900));
        mainJPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


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
