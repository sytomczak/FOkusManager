package pl.sytomczak.fokusmanager.notes.notesview;


import pl.sytomczak.fokusmanager.dbutils.DBConnection;

import javax.swing.*;
import java.sql.Connection;

public class NotesView extends JFrame {
    private JTextArea notesArea;
    private JPanel notesJPanel;
    private JEditorPane titlePanel;
    private JButton newNoteButton;
    private JButton saveButton;
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton selectAllButton;
    private JTextField textField1;
    private JButton searchButton;


    public NotesView() {

        setSize(510, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(notesJPanel);
    }

    public static void main(String[] args) {
        NotesView notesView = new NotesView();
        notesView.setVisible(true);

      //  Connection connection = DBConnection.getConnection();


    }

}
