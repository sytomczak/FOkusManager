package pl.sytomczak.fokusmanager.notes.gui;

import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteThumbnailView extends JFrame{
    private JTextArea noteTextArea;
    private JPanel thumbnailPanel;
    private JButton unpinButton;
    private JPanel titlePanel;
    private JPanel notePanel;
    private JButton deleteButton;
    private JTextField titleTextField;

    NotesOperationsWithDatabase notesOperationsWithDatabase = new NotesOperationsWithDatabase(titleTextField, noteTextArea, thumbnailPanel);

    public NoteThumbnailView() {
        setSize(240, 225);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(thumbnailPanel);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.delete(titleTextField.getText());
                System.exit(0);
            }
        });

        unpinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        NoteThumbnailView notes = new NoteThumbnailView();
        notes.setVisible(true);
    }
}
