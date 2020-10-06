package pl.sytomczak.fokusmanager.calender.monthsview;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.CustomDrawElements;
import com.mindfusion.scheduling.awt.AwtCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class ActualMonthView extends JFrame {

    AwtCalendar calendar;

    protected ActualMonthView() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Calender");
        setMinimumSize(new Dimension(800, 600));

        BorderLayout layout = new BorderLayout();
        getContentPane().setLayout(layout);

        JLabel label = new JLabel();
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(label, BorderLayout.NORTH);

        calendar = new AwtCalendar();
        calendar.beginInit();
        calendar.setCurrentTime(DateTime.now());
        DateTime today = DateTime.today();

        //  calendar.setDate(today.addMonths(3));               // dodatkowe miesiace
        //  calendar.getSelection().set(DateTime.today().addMonths(3));

        calendar.setDate(today);
        calendar.getSelection().set(DateTime.today());

        calendar.setCurrentView(CalendarView.SingleMonth);
        calendar.setCustomDraw(CustomDrawElements.CalendarItem);
        calendar.getMonthSettings().getDaySettings().setHeaderSize(20);
        calendar.getItemSettings().setSize(32);
        calendar.endInit();


        getContentPane().add(calendar, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ActualMonthView window = null;
                try {
                    window = new ActualMonthView();
                    window.setVisible(true);
                } catch (Exception exp) {
                }
            }
        });
    }

}
