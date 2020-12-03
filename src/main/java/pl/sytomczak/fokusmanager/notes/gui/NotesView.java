package pl.sytomczak.fokusmanager.notes.gui;


import pl.sytomczak.fokusmanager.calendar.gui.CalendarMainView;
import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JButton photoButton;
    private JButton deleteButton;
    private String patchToYellowImage = "/g2.png";
    private String patchToGreenImage = "/gg1.png";
    public Boolean pressedButton = false;

    NotesOperationsWithDatabase notesOperationsWithDatabase;
    NoteThumbnailView noteThumbnailView = new NoteThumbnailView();

    public NotesView() {
        setSize(540, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(notesJPanel);

        initializeButtons();

        notesOperationsWithDatabase = new NotesOperationsWithDatabase(titleField, notesArea, notesJPanel);

        setIconsInPhotoButton(photoButton);

        titleField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                titleField.setText("");
            }
        });
    }



    public void setIconsInPhotoButton(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pressedButton == false) {
                    btn.setIcon(new ImageIcon(getClass().getResource(patchToYellowImage)));
                    pressedButton = true;


                    noteThumbnailView.pack();
                    noteThumbnailView.setResizable(false);
                    noteThumbnailView.setVisible(true);
                    noteThumbnailView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    noteThumbnailView.setLocationRelativeTo(null);


                   // calendarMainView.getComponent(notesJPanel.add(noteThumbnailView.setVisible(true)));

                    //noteThumbnailView.add(calendarMainView.getContentPane(notesJPanel));


                } else if (pressedButton == true) {
                    btn.setIcon(new ImageIcon(getClass().getResource(patchToGreenImage)));
                    pressedButton = false;

                    noteThumbnailView.dispose();

                }
            }
        });
    }

    private void initializeButtons() {

        newNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.New();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    notesOperationsWithDatabase.save(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        cutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.cut();
            }
        });
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.copy();
            }
        });
        pasteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.paste();
            }
        });
        selectAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                notesOperationsWithDatabase.selectAll();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.search(notesArea, searchField.getText());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.delete(titleField.getText());
            }
        });
    }

    public static void main(String[] args) {
        NotesView notesView = new NotesView();
        notesView.setVisible(true);
    }

}
