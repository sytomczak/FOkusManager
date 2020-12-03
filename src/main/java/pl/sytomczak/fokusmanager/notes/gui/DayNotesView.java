package pl.sytomczak.fokusmanager.notes.gui;

import pl.sytomczak.fokusmanager.calendar.Date;
import pl.sytomczak.fokusmanager.calendar.gui.CalendarMainView;
import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DayNotesView extends JFrame {


    private JPanel dayJPanel;
    private JPanel settingsJPanel;
    private JPanel noteJPanel;
    private JPanel dayNotesJPanel;
    private JTextArea noteTextArea;
    private JPanel optionJPanel;
    private JTextField dayTextField;
    private JButton saveButton;
    private JButton addCategoryButton;
    private JPanel dateJPanel;
    private JButton photoButton;
    private JEditorPane dateEditorPane;
    private JButton deleteButton;

    NotesOperationsWithDatabase notesOperationsWithDatabase;
    NotesView notesView = new NotesView();
    CalendarMainView calendarMainView = new CalendarMainView();

    public DayNotesView() {
        initialize();
    }

    public DayNotesView(String dayName) {
        initialize();
        dayTextField.setText(dayName);
    }

    private void initialize() {
        setSize(455, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(dayNotesJPanel);

        initializeButton();

        notesOperationsWithDatabase = new NotesOperationsWithDatabase(dayTextField, noteTextArea, dayNotesJPanel);

        notesView.setIconsInPhotoButton(photoButton);

        Date.date(dateEditorPane);
        dateEditorPane.setSize(100,30);
        dateEditorPane.setLocation(100,101);

    }

    private void initializeButton() {

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    notesOperationsWithDatabase.save(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.delete(dayTextField.getText());
            }
        });
    }



    public static void main(String[] args) {
        DayNotesView dayNotesView = new DayNotesView();
        dayNotesView.setVisible(true);
    }

}


