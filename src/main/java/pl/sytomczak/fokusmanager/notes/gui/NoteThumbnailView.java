package pl.sytomczak.fokusmanager.notes.gui;

import javax.swing.*;

public class NoteThumbnailView extends JFrame{
    private JTextArea titleTextArea;
    private JTextArea noteTextArea;
    private JPanel thumbnailPanel;
    private JButton unpinButton;
    private JPanel titlePanel;
    private JPanel notePanel;
    private JButton deleteButton;

    public NoteThumbnailView() {
        setSize(240, 225);
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
