package pl.sytomczak.fokusmanager.notes;

import pl.sytomczak.fokusmanager.dbutils.DBConnection;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotesOperationsWithDatabase {

    private JTextArea notesArea;
    private JTextField titleField;
    private JPanel panel;
    private String filePath;
    private NotepadModel notepadModel = new NotepadModel();
    private NotepadItem notepadItem = new NotepadItem();
    private String orygText;

    private String title() {
        return titleField.getText();
    }

    public NotesOperationsWithDatabase(JTextField titleField, JTextArea notesArea, JPanel panel) {
        if (notesArea == null)
            return;

        this.titleField = titleField;
        this.notesArea = notesArea;
        this.panel = panel;
    }

    public void newNote() {
        if (notesArea == null)
            return;
        notesArea.selectAll();
        notesArea.replaceSelection("");
        notepadItem = new NotepadItem();
        orygText = "";
        titleField.setText("");
    }

    public void openDialog(String text) throws SQLException {
        notepadItem = notepadModel.getItemByText(text);
        if (notepadItem != null)
            orygText = notepadItem.getText();
    }

    public void open() throws Exception {
        openDialog(title());
        if (notesArea == null)
            return;

        try {
            openDialog(title());
            if (notepadItem != null) {
                notesArea.setText(notepadItem.getText());
                titleField.setText(notepadItem.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NotepadItem getItem(String title) throws SQLException {
        if(title == null)
            return null;

        return notepadModel.getItemByTitle(title);
    }

    public void save(Boolean showDialog) throws Exception {
        boolean save = true;

        if (save == showDialog) {
            notepadModel.addText(title(), notepadItem.getText());
        }
        if (notesArea == null)
            return;

        if (notepadItem == null)
            System.out.println("SelectedItem is null (method Save in NotepadOperationsWithDatabase)");

        String content;
        content = notesArea.getText();
        if (notepadItem != null) {
            notepadItem.setText(content);
            notepadItem.setTitle(title());
        }

        try {
            if (notepadItem != null) {
                if (notepadModel.exist(title()))
                    notepadModel.update(title(), notepadItem.getText());
                else
                    notepadModel.addText(title(), notepadItem.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cut() {
        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String cutString = notesArea.getSelectedText();
        StringSelection cutSelection = new StringSelection(cutString);
        clipboard.setContents(cutSelection, cutSelection);
        notesArea.replaceRange("", notesArea.getSelectionStart(), notesArea.getSelectionEnd());

    }

    public void copy() {

        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String copyText = notesArea.getSelectedText();
        StringSelection copySelection = new StringSelection(copyText);
        clipboard.setContents(copySelection, copySelection);
    }


    public void paste() {
        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
            Transferable pasteText = clipboard.getContents(notesArea);
            String str = (String) pasteText.getTransferData(DataFlavor.stringFlavor);
            notesArea.replaceRange(str, notesArea.getSelectionStart(), notesArea.getSelectionEnd());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectAll() {
        notesArea.selectAll();
    }


    class myHighlighter extends DefaultHighlighter.DefaultHighlightPainter {
        public myHighlighter(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter highlightPainter = new NotesOperationsWithDatabase.myHighlighter(Color.yellow);


    public void removeHighLight(JTextComponent textComponent) {
        Highlighter removeHighlighter = textComponent.getHighlighter();
        Highlighter.Highlight[] remove = removeHighlighter.getHighlights();

        for (int i = 0; i < remove.length; i++) {
            if (remove[i].getPainter() instanceof NotesOperationsWithDatabase.myHighlighter) {
                removeHighlighter.removeHighlight(remove[i]);
            }
        }
    }

    public void search(JTextComponent textComponent, String textString) {
        removeHighLight(textComponent);

        try {
            Highlighter highlighter = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            while ((pos = text.toUpperCase().indexOf(textString.toUpperCase(), pos)) >= 0) {
                highlighter.addHighlight(pos, pos + textString.length(), highlightPainter);
                pos += textString.length();
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void delete(String title) {
        String sql = "DELETE FROM notes WHERE title = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
