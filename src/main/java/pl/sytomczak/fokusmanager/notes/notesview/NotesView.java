package pl.sytomczak.fokusmanager.notes.notesview;


import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jgoodies.forms.layout.CellConstraints;

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

    NotesOperationsWithDatabase notesOperationsWithDatabase;

    public NotesView() {

        //GridLayout gl = new GridLayout(5, 5);
        //CellConstraints cc = new CellConstraints();


        //notesJPanel.remove(notesAreaScrollPanel);
         //add(new JTextArea(), cc.rchw(0,0,3,1));
        //notesJPanel.add(notesAreaScrollPanel);
        /*JButton goldSeat;
        JButton silverSeat;
        JButton bronzeSeat;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
            {
                goldSeat = new JButton();
                goldSeat.setSize(100,25);
                silverSeat = new JButton();
                silverSeat.setSize(100,25);
                bronzeSeat = new JButton();
                bronzeSeat.setSize(100,25);
                add(new JButton());
            }*/
        //this.add(notesJPanel);


        setSize(510, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(notesJPanel);
        InitializeButtons();

        //JScrollPane scrollPane = new JScrollPane(notesArea);//, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane.setBounds(20,20,500,400);
        //scrollPane.getViewport().setBackground(Color.WHITE);


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
