package pl.sytomczak.fokusmanager.notes.notesview;


import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesView extends JFrame {
    private JPanel notesJPanel;
    private JEditorPane titlePanel;
    private JButton newNoteButton;
    private JButton saveButton;
    private JButton cutButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JButton selectAllButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextField titleField;
    private JTextArea notesArea;
    private JButton addCategoryButton;
    private JButton selectCategoryButton;
    private JButton selectColorButton;

    NotesOperationsWithDatabase notesOperationsWithDatabase;

    public NotesView() {


        setSize(510, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(notesJPanel);
        InitializeButtons();


        notesOperationsWithDatabase = new NotesOperationsWithDatabase(titleField, notesArea, notesJPanel);
    }

    private void InitializeButtons() {

        newNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.New();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    notesOperationsWithDatabase.Save(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        cutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.Cut();
            }
        });
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.Copy();
            }
        });
        pasteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.Paste();
            }
        });
        selectAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                notesOperationsWithDatabase.SelectAll();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.Search(notesArea, searchField.getText());
            }
        });
    }

    public static void main(String[] args) {
        NotesView notesView = new NotesView();
        notesView.setVisible(true);
    }

}
