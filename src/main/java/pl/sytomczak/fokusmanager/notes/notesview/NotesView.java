package pl.sytomczak.fokusmanager.notes.notesview;


import pl.sytomczak.fokusmanager.dbutils.DBConnection;

import javax.swing.*;
import java.sql.Connection;

public class NotesView extends JFrame {
    private JTextArea notesArea;
    private JPanel notesJPanel;
    private JEditorPane titlePanel;


    public NotesView() {

        setSize(400, 400);
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
