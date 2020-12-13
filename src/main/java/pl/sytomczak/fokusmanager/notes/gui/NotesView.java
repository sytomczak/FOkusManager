package pl.sytomczak.fokusmanager.notes.gui;


import pl.sytomczak.fokusmanager.calendar.gui.CalendarMainView;
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
    private JButton openButton;
    private String patchToYellowImage = "/g2.png";
    private String patchToGreenImage = "/gg1.png";
    private int counter;
    private JPanel noteThumbnailJPanel;
    private int thumbnailCounter;
    private String patchToDeleteIcon = "/x.png";
    private String patchToUnpinIcon = "/pin3.png";

    public Boolean pressedButton = false;
    private Boolean isNoteAdded = false;

    NotesOperationsWithDatabase notesOperationsWithDatabase;

    public NotesView() {
        Initialize();
    }

    public NotesView(JPanel mainNotesJPanel) {
        this.mainNotesJPanel = mainNotesJPanel;
        Initialize();
    }

    private void Initialize() {
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


                    noteThumbnailJPanel = new JPanel(new GridLayout(4, 3));
                    noteThumbnailJPanel.setName("noteThumbnailJPanel");
                    noteThumbnailJPanel.setBackground(Color.BLUE);
                    noteThumbnailJPanel.setSize(titleWidth + 70 + 20, titleHeight + descriptionHeight + 10 + 50);

                    int locationX = jPanel.getLocation().x;
                    if (isNoteAdded == false)
                        locationX = 0;

                    int locationY = jPanel.getLocation().y;
                    if (locationY > 100)
                        locationY = 50;
                    if (locationX > 1000) {
                        locationX = 0;
                        locationY += 0;
                    }

                    JTextArea title = addTitleInThumbnail(titleWidth, titleHeight, noteThumbnailJPanel);
                    JTextArea description = addDescriptionInThumbnail(descriptionWidth, descriptionHeight, titleHeight, noteThumbnailJPanel);

                    JTextArea buttons = nowyPanelD("eeeee", 50, 10, noteThumbnailJPanel, description, new Point(titleWidth + 5, 0), "Kliknij, żeby wyczyścić tytuł", ThumbnailTextAreaUsedControl.TITLE);


                    JButton btn = nowyButton("btn", 100, 25, noteThumbnailJPanel);
                    btn.setSize(100, 30);
                    btn.setLocation(0, 0);


                    if (counter == 0) {
                        locationX = 0;
                        counter++;
                    } else {
                        if (isNoteAdded)
                            locationX = locationX + 250;
                        else
                            locationX = 0;
                        counter++;
                    }
                    noteThumbnailJPanel.setLocation(locationX + 20, 50);

                    CalendarMainView.instance.NotesJPanel().add(noteThumbnailJPanel);
                }

            } catch (Exception ex) {
                String msg = ex.getMessage();
            }
        }
    }

    private JButton nowyButton(String text, int width, int height, JPanel parentPanel) {
        JPanel panel = new JPanel();

        JButton btn = new JButton(text);
        btn.setSize(width, height);
        btn.setLocation(0, 0);
        btn.setName(text);
        panel.add(btn);

        btn = new JButton(text);
        btn.setSize(width, height);
        btn.setLocation(0, 0);
        btn.setName(text + " " + text);
        panel.add(btn);
        parentPanel.add(panel);
        return btn;
    }

    // odczyt automatycznie tytulu i calej notatki czyli moze byc chyba jedna metoda lub 2 ustawienie tytulu i doczytu wielkosc itp plus osobna ze te 2 panele po zaznaczaeniu zoltej pinezki automatycznie sie odczytuja

    private JTextArea createThumbnailTextArea(String note, int location, int width, int height, JPanel parentPanel) {
        if (parentPanel == null)
            return null;

        JTextArea noteTextArea = new JTextArea();
        noteTextArea.setSize(width, height);
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setText(note);

        JScrollPane jScrollPaneNoteArea = new JScrollPane(noteTextArea);
        jScrollPaneNoteArea.setSize(width, height);
        jScrollPaneNoteArea.setLocation(0, location);
        jScrollPaneNoteArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        parentPanel.add(jScrollPaneNoteArea);
        return noteTextArea;
    }


    private JTextArea addTitleInThumbnail(int width, int height, JPanel parentPanel) {
        if (width < 0)
            width = 100;
        if (height < 0)
            height = 40;

        JTextArea titleArea = createThumbnailTextArea("title", 0, width, height, parentPanel);
        return titleArea;
    }

    private JTextArea addDescriptionInThumbnail(int width, int height, int titleHeight, JPanel parentPanel) {
        if (width < 0)
            width = 100;
        if (titleHeight < 0)
            titleHeight = 40;
        if (height < 0)
            height = 100;

        JTextArea noteTextArea = createThumbnailTextArea("description", 40, width, height, parentPanel);
        return noteTextArea;
    }


    private JTextArea nowyPanel(String note, int location, int width, int height, JPanel parentPanel) {
        if (parentPanel == null)
            return null;

        JTextArea nowa = new JTextArea();
        nowa.setSize(width, height);
        nowa.setLineWrap(true);
        nowa.setWrapStyleWord(true);
        nowa.setText(note);

        return nowa;
    }

    private JTextArea nowyPanelD(String text, int width, int height, JPanel parentPanel, JTextArea textArea, Point location, String toolTip, ThumbnailTextAreaUsedControl controlTypeForButtonClick) {
        if (parentPanel == null)
            return null;

        if (width < 0)
            width = 100;
        if (height < 0)
            height = 100;
        if (location == null)
            location = new Point(parentPanel.getWidth() - 50, 0);

        JTextArea nowypanel = nowyPanel("sssddd", 0, width, height, noteThumbnailJPanel);
        nowypanel.setSize(width, height);
        nowypanel.setLocation(location);
        nowypanel.setRows(2);
        parentPanel.add(nowypanel);
        parentPanel.repaint();
        parentPanel.revalidate();
        parentPanel.validate();
        return nowypanel;
    }


    //dodac opcje odpina notatke i odnzacza na zielona pinezke kobkretny dzien lub tytul notatki jedna metoda do 2 guzikow. / zrobic 2 metody i 2 osobne guziki ewentualnie jeden wyg;ad a potem robic na 2 i dodac foto i funckje
//     private void addButtonToTheRightOfTheTitleAndDescription(String text, int width, int height, JPanel parentPanel, JTextArea textArea, Point location, String toolTip, ThumbnailTextAreaUsedControl controlTypeForButtonClick) {
//        if (parentPanel == null)
//            return;
//
//        if (width < 0)
//            width = 10;
//        if (height < 0)
//            height = 10;
//        if (location == null)
//            location = new Point(parentPanel.getWidth() - 50, 0);
//
//        JButton btn = new JButton(text);
//        btn.setSize(width, height);
//        btn.setLocation(location);
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                if (controlTypeForButtonClick == ThumbnailTextAreaUsedControl.TITLE && textArea != null && textArea.getName() == "title_textArea")
////                    textArea.setText(null);
////              else if (controlTypeForButtonClick == ThumbnailTextAreaUsedControl.DESCRIPTION && textArea.getName() == "description_textArea")
////                    textArea.setText(null);
//                if (noteThumbnailJPanel.isVisible() == true) {
//                    dispose();
//                    noteThumbnailJPanel.setVisible(false);
//                }
//
//            }
//        });
//        btn.setToolTipText(toolTip);
//        parentPanel.add(btn);
//        parentPanel.repaint();
//        parentPanel.revalidate();
//        parentPanel.validate();
//    }


    public enum ThumbnailTextAreaUsedControl {
        TITLE,
        DESCRIPTION
    }


    private int limitCreateThumbnailPanel() {

      //  createThumbnailPanel();
        isNoteAdded = true;

        if (mainNotesJPanel != null && thumbnailCounter < 4)
            createThumbnailPanel();
        return thumbnailCounter++;

    }

    public void setIconsInPhotoButton(JButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pressedButton == false) {
                    limitCreateThumbnailPanel();
                    btn.setIcon(new ImageIcon(getClass().getResource(patchToYellowImage)));
                    pressedButton = true;
//                    try {
//                        notesOperationsWithDatabase.open();
//                    } catch (Exception exception) {
//                        exception.printStackTrace();
//
//                    }


                } else if (pressedButton == true) {
                    btn.setIcon(new ImageIcon(getClass().getResource(patchToGreenImage)));
                    pressedButton = false;
                    for (int i = 0; i < mainNotesJPanel.getComponentCount(); i++) {
                        if (mainNotesJPanel.getComponent(i).getName() == "noteThumbnailJPanel")
                            mainNotesJPanel.getComponent(i).setVisible(false);
                    }
                    isNoteAdded = false;
                    //noteThumbnailJPanel.setVisible(false);


                }
            }
        });
    }

    private void initializeButtons() {

        newNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notesOperationsWithDatabase.newNote();
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

//        openButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    notesOperationsWithDatabase.open();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            }
//        });
    }

    public static void main(String[] args) {
        NotesView notesView = new NotesView();
        notesView.setVisible(true);
    }

}
