//package pl.sytomczak.fokusmanager.calendar.jdatapickercalenaer;
//
//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Properties;
//
//public class JDataPickerView {
//
//    public static void main(String[] args) {
//        new JDataPickerView();
//    }
//
//    public JDataPickerView() {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    ex.printStackTrace();
//                }
//
//                JFrame frame = new JFrame("Calender");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.add(new CalenderPane());
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setSize(300, 300);
//                frame.setVisible(true);
//            }
//        });
//    }
//
//    public class CalenderPane extends JPanel {
//
//        public CalenderPane() {
//            UtilDateModel model = new UtilDateModel();
//            Properties p = new Properties();
//            p.put("text.today", "Today");
//            p.put("text.month", "Month");
//            p.put("text.year", "Year");
//            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//            setLayout(new GridBagLayout());
//            add(datePicker);
//
//
//        }
//
//    }
//
//    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
//
//        private String datePattern = "yyyy-MM-dd";
//        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
//
//        @Override
//        public Object stringToValue(String text) throws ParseException {
//            return dateFormatter.parseObject(text);
//        }
//
//        @Override
//        public String valueToString(Object value) throws ParseException {
//            if (value != null) {
//                Calendar cal = (Calendar) value;
//                return dateFormatter.format(cal.getTime());
//            }
//
//            return "";
//        }
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
