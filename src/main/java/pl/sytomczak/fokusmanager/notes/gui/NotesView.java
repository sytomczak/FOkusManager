package pl.sytomczak.fokusmanager.notes.gui;


import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NotesView extends JFrame {
    private JPanel notesJPanel;
    private JPanel mainNotesJPanel;
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

    public NotesView(JPanel mainNotesJPanel) {
        this.mainNotesJPanel = mainNotesJPanel;

        setSize(540, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes 2");
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

    private void addNoteThumbnailView() {
        if (mainNotesJPanel != null)
            try {

                JComponent jPanel = (JComponent) mainNotesJPanel.getComponent(mainNotesJPanel.getComponentCount() - 1);
                if (jPanel != null) {

                    JPanel noteThumbnailJPanel = new JPanel();
                    noteThumbnailJPanel.setSize(240, 225);
                    int locationX = jPanel.getLocation().x;
                    int locationY = jPanel.getLocation().y;
                    if (locationY > 100)
                        locationY = 50;
                    if (locationX > 500) {
                        locationX = 0;
                        locationY += 0;
                    }

                    JTextArea noteTitleTextField= new JTextArea();
                    noteTitleTextField.setLocation(0, 0);
                    noteTitleTextField.setSize(200, 40);
                    noteTitleTextField.setLineWrap(true);
                    noteTitleTextField.setWrapStyleWord(true);
                    noteTitleTextField.setText("test");


                    JScrollPane jScrollPaneTitleNoteArea = new JScrollPane();
                    jScrollPaneTitleNoteArea.setSize(200, 40);
                    jScrollPaneTitleNoteArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    jScrollPaneTitleNoteArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    jScrollPaneTitleNoteArea.add(noteTitleTextField);


                    JTextField noteTextArea = new JTextField();
                    noteTextArea.setLocation(0, 40);
                    noteTextArea.setSize(200, 150);
                    noteTextArea.setText("note");
                  //  noteTextArea.setLineWrap(true);

                    JScrollPane jScrollPaneNoteArea = new JScrollPane();
                    jScrollPaneNoteArea.setSize(200, 150);
                    jScrollPaneNoteArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    jScrollPaneNoteArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    jScrollPaneNoteArea.add(noteTextArea);


                    noteThumbnailJPanel.setLocation(locationX + jScrollPaneTitleNoteArea.getWidth() + 0, locationY);
                    noteThumbnailJPanel.setLocation(locationX + jScrollPaneNoteArea.getWidth() + 40, locationY);

                    noteThumbnailJPanel.add(jScrollPaneTitleNoteArea);
                    noteThumbnailJPanel.add(jScrollPaneNoteArea);

                    mainNotesJPanel.add(noteThumbnailJPanel);
                }

            } catch (Exception ex) {
                String msg = ex.getMessage();
            }
    }

    public void setIconsInPhotoButton(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pressedButton == false) {

                    if (mainNotesJPanel != null)
                        addNoteThumbnailView();

                    btn.setIcon(new ImageIcon(getClass().getResource(patchToYellowImage)));
                    pressedButton = true;


                } else if (pressedButton == true) {
                    btn.setIcon(new ImageIcon(getClass().getResource(patchToGreenImage)));
                    pressedButton = false;

                    //   noteThumbnailView.dispose();

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
