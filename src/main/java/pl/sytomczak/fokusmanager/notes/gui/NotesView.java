package pl.sytomczak.fokusmanager.notes.gui;


import org.jfree.chart.title.Title;
import pl.sytomczak.fokusmanager.notes.NotesOperationsWithDatabase;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import javax.xml.soap.Text;
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
    private int counter;
    private  JButton open;

    public Boolean pressedButton = false;

    NotesOperationsWithDatabase notesOperationsWithDatabase;

    public NotesView() {
        setSize(540, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Notes");
        setContentPane(notesJPanel);

        notesOperationsWithDatabase = new NotesOperationsWithDatabase(titleField, notesArea, notesJPanel);
        setIconsInPhotoButton(photoButton);

        titleField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                titleField.setText("");
            }
        });


        initializeButtons();
        createThumbnailPanel();
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

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    notesOperationsWithDatabase.open();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private void createThumbnailPanel() {
        if (mainNotesJPanel != null) {
            try {

                JComponent jPanel = (JComponent) mainNotesJPanel.getComponent(mainNotesJPanel.getComponentCount() - 1);
                if (jPanel != null) {

                    int titleWidth = 200;
                    int titleHeight = 40;
                    int descriptionWidth = 200;
                    int descriptionHeight = 150;

                    JPanel noteThumbnailJPanel = new JPanel();
                    noteThumbnailJPanel.setSize(titleWidth + 70, titleHeight + descriptionHeight + 10);

                    int locationX = jPanel.getLocation().x;
                    int locationY = jPanel.getLocation().y;
                    if (locationY > 100)
                        locationY = 50;
                    if (locationX > 1000) {
                        locationX = 0;
                        locationY += 0;
                    }

                    JTextArea title = addTitleInThumbnail("", titleWidth, titleHeight, true, noteThumbnailJPanel);
                    JTextArea description = addDescriptionInThumbnail("", descriptionWidth, descriptionHeight, titleHeight, true, noteThumbnailJPanel);


                    if (counter == 0) {
                        locationX = 0;
                        counter++;
                    } else {
                        locationX = locationX + titleWidth;
                        counter++;
                    }
                    noteThumbnailJPanel.setLocation(locationX + 40, locationY);

                    addButtonToTheRightOfTheTitleAndDescription("eeeee", 20, 20, noteThumbnailJPanel, title, new Point(titleWidth + 5, 0), "Kliknij, żeby wyczyścić tytuł", ThumbnailTextAreaUsedControl.TITLE);
                    addButtonToTheRightOfTheTitleAndDescription("aaa", 20, 20, noteThumbnailJPanel, description, new Point(descriptionWidth + 5, descriptionHeight + titleHeight / 2), "Kliknij, żeby wyczyścić opis ", ThumbnailTextAreaUsedControl.DESCRIPTION);


             //       noteThumbnailJPanel.setBackground(Color.getHSBColor(82,107,242));
                    mainNotesJPanel.add(noteThumbnailJPanel);
//                    mainNotesJPanel.repaint();
//                    mainNotesJPanel.revalidate();
//                    mainNotesJPanel.validate();
                }

            } catch (Exception ex) {
                String msg = ex.getMessage();
            }
        }
    }

    private JTextArea addTitleInThumbnail(String title, int width, int height, boolean isWrap, JPanel parentPanel) {
        if (parentPanel == null)
            return null;

        if (width < 0)
            width = 100;
        if (height < 0)
            height = 40;

        JTextArea noteTitleTextField = new JTextArea();
        noteTitleTextField.setName("title_textArea");
        noteTitleTextField.setSize(width, height);
        noteTitleTextField.setLineWrap(isWrap);
        noteTitleTextField.setWrapStyleWord(true);
        noteTitleTextField.setText(title);

        JScrollPane jScrollPaneTitleNoteArea = new JScrollPane(noteTitleTextField);
        jScrollPaneTitleNoteArea.setName("title_scrollPane");
        jScrollPaneTitleNoteArea.setSize(width, height);
        jScrollPaneTitleNoteArea.setLocation(0,0);
        jScrollPaneTitleNoteArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        parentPanel.add(jScrollPaneTitleNoteArea);
//        parentPanel.repaint();
//        parentPanel.revalidate();
//        parentPanel.validate();
        return noteTitleTextField;
    }

    private JTextArea addDescriptionInThumbnail(String description, int width, int height, int titleHeight, boolean isWrap, JPanel parentPanel) {
        if (parentPanel == null)
            return null;

        if (width < 0)
            width = 100;
        if(titleHeight <0)
            titleHeight = 40;
        if (height < 0)
            height = 100;

        JTextArea noteTextArea = new JTextArea();
        noteTextArea.setName("description_textArea");
        noteTextArea.setSize(width, height);
        noteTextArea.setText(description);
        noteTextArea.setLineWrap(isWrap);

        JScrollPane jScrollPaneNoteArea = new JScrollPane(noteTextArea);
        jScrollPaneNoteArea.setName("description_scrollPane");
        jScrollPaneNoteArea.setLocation(0, titleHeight);
        jScrollPaneNoteArea.setSize(width, height);
        jScrollPaneNoteArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        parentPanel.add(jScrollPaneNoteArea);
//        parentPanel.repaint();
//        parentPanel.revalidate();
//        parentPanel.validate();
        return noteTextArea;
    }






    private void addButtonToTheRightOfTheTitleAndDescription(String text, int width, int height, JPanel parentPanel, JTextArea textArea, Point location, String toolTip, ThumbnailTextAreaUsedControl controlTypeForButtonClick) {
        if (parentPanel == null)
            return;

        if (width < 0)
            width = 10;
        if (height < 0)
            height = 10;
        if(location== null)
            location = new Point(parentPanel.getWidth()-50, 0);

        JButton btn = new JButton(text);
        btn.setSize(width, height);
        btn.setLocation(location);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlTypeForButtonClick == ThumbnailTextAreaUsedControl.TITLE && textArea != null && textArea.getName() == "title_textArea")
                    textArea.setText(null);
                else if (controlTypeForButtonClick == ThumbnailTextAreaUsedControl.DESCRIPTION && textArea.getName() == "description_textArea")
                    textArea.setText(null);

            }
        });
        btn.setToolTipText(toolTip);
        parentPanel.add(btn);
        parentPanel.repaint();
        parentPanel.revalidate();
        parentPanel.validate();
    }


    public enum ThumbnailTextAreaUsedControl
    {
        TITLE,
        DESCRIPTION
    }







    public void setIconsInPhotoButton(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pressedButton == false) {

                    if (mainNotesJPanel != null)
                        createThumbnailPanel();

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
