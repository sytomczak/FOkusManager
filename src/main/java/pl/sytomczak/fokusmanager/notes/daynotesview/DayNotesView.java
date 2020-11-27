package pl.sytomczak.fokusmanager.notes.daynotesview;

import pl.sytomczak.fokusmanager.calendar.mainView.CalendarMainView;
import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;
import pl.sytomczak.fokusmanager.notes.notesview.NotesView;

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
    private JButton selectCategoryButton;
    private JButton selectColorButton;
    private JButton addCategoryButton;
    private JPanel dateJPanel;
    private JButton photoButton;

    NotesOperationsWithDatabase notesOperationsWithDatabase;
    NotesView notesView = new NotesView();
    CalendarMainView calendarMainView = new CalendarMainView();

    public DayNotesView() {
        Initialize();
    }

    public DayNotesView(String dayName) {
        Initialize();
        dayTextField.setText(dayName);
    }

    private void Initialize() {
        setSize(540, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(dayNotesJPanel);

        InitializeButtons();

        notesOperationsWithDatabase = new NotesOperationsWithDatabase(dayTextField, noteTextArea, dayNotesJPanel);

        notesView.SetIconsInPhotoButton(photoButton);

    }

    private void InitializeButtons() {

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    notesOperationsWithDatabase.Save(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        DayNotesView dayNotesView = new DayNotesView();
        dayNotesView.setVisible(true);
    }

}


