package pl.sytomczak.fokusmanager.notes.notethumbnail;

import pl.sytomczak.fokusmanager.notes.notesview.NotesView;

import javax.swing.*;

public class NoteThumbnailView extends JFrame{
    private JTextArea titleTextArea;
    private JTextArea noteTextArea;
    private JPanel thumbnailPanel;
    private JButton deleteButton;
    private JPanel titlePanel;

    public NoteThumbnailView() {
        setSize(210, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(thumbnailPanel);
    }
    public static void main(String[] args) {
        NoteThumbnailView notes = new NoteThumbnailView();
        notes.setVisible(true);
    }

}
