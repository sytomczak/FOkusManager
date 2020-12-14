package pl.sytomczak.fokusmanager.notes.gui;

import javax.swing.*;
import java.awt.*;

public class UserDetail extends JPanel {
    public JPanel Panel1;

    public UserDetail() {
        this.setSize(200, 200);
        Panel1 = new JPanel(new BorderLayout());

        AddTitle("testowe title");
        AddDescription("testowy opis");
        JButton btn = new JButton("OK");
        btn.setSize(100,20);
        Panel1.add(btn);
        Panel1.repaint();
        Panel1.revalidate();

        this.repaint();
        this.revalidate();
    }

    private void AddTitle(String text) {
        JTextArea jta = new JTextArea(text);
        jta.setSize(this.getWidth(), 40);
        Panel1.add(jta, BorderLayout.NORTH);
        Panel1.repaint();
        Panel1.revalidate();
    }

    private void AddDescription(String text) {
        JTextArea jta = new JTextArea(text);
        Panel1.add(jta, BorderLayout.SOUTH);
        Panel1.repaint();
        Panel1.revalidate();
    }
}