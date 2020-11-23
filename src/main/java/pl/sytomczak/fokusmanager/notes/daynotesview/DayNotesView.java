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

    NotesOperationsWithDatabase notesOperationsWithDatabase;
    NotesView notesView = new NotesView();

    

    //po otwarciu dnia automatyczny odczyt i zapisuje tytul jako numer dnia

    public DayNotesView() {

        setSize(510, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(dayNotesJPanel);

        InitializeButtons();
        dayTextField.setText(dayNumber());

        notesOperationsWithDatabase = new NotesOperationsWithDatabase(dayTextField, noteTextArea, dayNotesJPanel);
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

    public String dayNumber() {
        CalendarMainView calendarMainView = new CalendarMainView();
        return calendarMainView.dayButton.getText();
 //pobieranie numeru kliknietego dnia
        }


    public static void main(String[] args) {
        DayNotesView dayNotesView = new DayNotesView();
        dayNotesView.setVisible(true);
    }

}
