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
import java.util.ArrayList;
import java.util.HashMap;


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
                notesView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                notesView.setLocationRelativeTo(null);
            }
        });

        dayButton.addActionListener(new ActionListener() {
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

        Clock.runClock(clockPanel);

        ActualMonth.setActualMonth(monthPanel);

        ActualYear.getActualYear(yearsPanel);

        controlsInActualMonth(); // z zakomentowanym brak przyciskow

        /*ArrayList<String> s = new ArrayList<>();
        HashMap<String, Component> s2 = new HashMap<>();
        String sss;
        for(int i=0;i<calendarJPanel.getComponentCount();i++)
        {
            if(calendarJPanel.getComponent(i) instanceof  JButton)
            {
                sss = "JButton - " +((JButton) calendarJPanel.getComponent(i)).getText() + "\n";
                s2.put(sss, (JButton) calendarJPanel.getComponent(i));
                s.add(sss);
            }else if(calendarJPanel.getComponent(i) instanceof  JEditorPane)
            {
                if(((JEditorPane) calendarJPanel.getComponent(i)).getText().contains("LISTO"))
                    sss = "JEditorPane - " +((JEditorPane) calendarJPanel.getComponent(i)).getText() + " - indeks - " + i+"\n";
                else
                    sss = "JEditorPane - " +((JEditorPane) calendarJPanel.getComponent(i)).getText() + "\n";
                s2.put(sss, (JEditorPane) calendarJPanel.getComponent(i));
                s.add(sss);
            }

        }

         */
        JEditorPane listopad = (JEditorPane) calendarJPanel.getComponent(3);
        //calendarJPanel.remove(3);
        //listopad.setVisible(true);
        //listopad.setLocation(50,50);
        listopad.setSize(130, 30);
        //calendarJPanel.add(listopad);

        //String ssss = String.valueOf("size: "+listopad.getSize() + " - location: "+listopad.getLocation() + " - isVisible: "+listopad.isVisible());
    }

    private void createControls() {
        dayButton = new JButton();
        dayButton.addActionListener(new ActionListener() {
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
        dayButton.setBackground(Color.yellow);
        calendarJPanel.setLayout(null);
        dayButton.setSize(80, 80);
        calendarJPanel.add(dayButton);
    }

    public void controlsInActualMonth() {

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
        System.out.println(months.daysInMonth());
        System.out.println(months.numberOfDaysToTheFirstMondayInActualMonth());


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainJPanel = new JPanel();
        mainJPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        calendarJPanel = new JPanel();
        calendarJPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        calendarJPanel.setBackground(new Color(-894145));
        mainJPanel.add(calendarJPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1100, 600), new Dimension(1100, 600), new Dimension(1100, 600), 0, false));
        calendarTitle = new JTextField();
        calendarTitle.setBackground(new Color(-905690));
        calendarTitle.setEditable(false);
        calendarTitle.setEnabled(false);
        Font calendarTitleFont = this.$$$getFont$$$(null, -1, 16, calendarTitle.getFont());
        if (calendarTitleFont != null) calendarTitle.setFont(calendarTitleFont);
        calendarTitle.setText("Calendar");
        calendarJPanel.add(calendarTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(70, 32), null, 0, false));
        yearsRadioButton = new JRadioButton();
        yearsRadioButton.setBackground(new Color(-2088718));
        yearsRadioButton.setHideActionText(false);
        yearsRadioButton.setSelected(false);
        yearsRadioButton.setText("");
        calendarJPanel.add(yearsRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clockPanel = new JEditorPane();
        clockPanel.setBackground(new Color(-8129971));
        clockPanel.setEditable(false);
        Font clockPanelFont = this.$$$getFont$$$(null, -1, 18, clockPanel.getFont());
        if (clockPanelFont != null) clockPanel.setFont(clockPanelFont);
        clockPanel.setForeground(new Color(-16777216));
        calendarJPanel.add(clockPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(100, 30), new Dimension(611, 30), new Dimension(100, 30), 0, false));
        monthPanel = new JEditorPane();
        monthPanel.setBackground(new Color(-8129971));
        monthPanel.setEditable(false);
        monthPanel.setEnabled(true);
        Font monthPanelFont = this.$$$getFont$$$(null, -1, 18, monthPanel.getFont());
        if (monthPanelFont != null) monthPanel.setFont(monthPanelFont);
        monthPanel.setForeground(new Color(-16777216));
        monthPanel.setText("<html>\n  <head>\n\n  </head>\n  <body>\n    <p style=\"margin-top: 0\">\n    </p>\n\n  </body>\n</html>\n");
        calendarJPanel.add(monthPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(130, 30), new Dimension(130, 30), new Dimension(130, 30), 0, false));
        yearsPanel = new JEditorPane();
        yearsPanel.setBackground(new Color(-8129971));
        yearsPanel.setEditable(false);
        yearsPanel.setEnabled(true);
        Font yearsPanelFont = this.$$$getFont$$$(null, -1, 18, yearsPanel.getFont());
        if (yearsPanelFont != null) yearsPanel.setFont(yearsPanelFont);
        yearsPanel.setForeground(new Color(-16777216));
        yearsPanel.setText("<html>   <head>    </head>   <body>     <p style=\"margin-top: 0\">     </p>    </body> </html> ");
        calendarJPanel.add(yearsPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        notesJPanel = new JPanel();
        notesJPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        notesJPanel.setBackground(new Color(-11375630));
        mainJPanel.add(notesJPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1100, 400), new Dimension(1100, 400), new Dimension(1100, 400), 0, false));
        notesTitle = new JTextField();
        notesTitle.setBackground(new Color(-11375630));
        notesTitle.setEditable(false);
        notesTitle.setEnabled(false);
        notesTitle.setText("Notes");
        notesJPanel.add(notesTitle, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        newNotesRadioButton = new JRadioButton();
        newNotesRadioButton.setBackground(new Color(-2088718));
        newNotesRadioButton.setEnabled(true);
        newNotesRadioButton.setSelected(false);
        newNotesRadioButton.setText("");
        notesJPanel.add(newNotesRadioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        notesJPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        legendJPanel = new JPanel();
        legendJPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        legendJPanel.setBackground(new Color(-855470));
        legendJPanel.setForeground(new Color(-855470));
        mainJPanel.add(legendJPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(300, -1), new Dimension(300, -1), new Dimension(300, -1), 0, false));
        legendTextField = new JTextField();
        legendTextField.setBackground(new Color(-855470));
        legendTextField.setEditable(false);
        legendTextField.setEnabled(false);
        legendTextField.setForeground(new Color(-855470));
        legendTextField.setText("Legend");
        legendJPanel.add(legendTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        legendJPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        legendJPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        legendBottom = new JRadioButton();
        legendBottom.setBackground(new Color(-2088718));
        legendBottom.setHideActionText(false);
        legendBottom.setSelected(false);
        legendBottom.setText("");
        legendJPanel.add(legendBottom, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainJPanel;
    }

}

