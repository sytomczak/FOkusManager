package pl.sytomczak.fokusmanager.notes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.sql.SQLException;

public class NotesOperationsWithDatabase {
    private JTextArea textArea;
    private JTextField titleField;
    private JPanel panel;
    private String filePath;
    private NotepadModel notepadModel = new NotepadModel();
    private NotepadItem notepadItem = new NotepadItem();
    private String orygText;

    private String title()
    {
        String title;
        title = titleField.getText();
        return title;
    }

    public NotesOperationsWithDatabase(JTextField titleField, JTextArea textArea, JPanel panel) {
        if (textArea == null)
            return;

        this.titleField = titleField;
        this.textArea = textArea;
        this.panel = panel;
    }

    public void New() {
        if (textArea == null)
            return;
        if (filePath != null)
            if (JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), "Do you want to overwrite changes") == 0)
                SaveNew();
        textArea.selectAll();
        textArea.replaceSelection("");
        notepadItem = new NotepadItem();
        orygText = "";
        titleField.setText("");
    }

    public void OpenDialog(String text) throws SQLException {
        notepadItem = notepadModel.getItemByText(text);
        if(notepadItem != null)
            orygText = notepadItem.getText();
    }

    public void SaveDialog(Integer nr, String text) throws SQLException {
        notepadModel.AddText(title(), text);
    }

    public void OpenOrSaveDialog(Boolean openOrSave) throws SQLException {

        boolean save = true;
        boolean open = true;

        if (openOrSave == open) {
            notepadItem = notepadModel.getItemByTitle(title());
            if (notepadItem != null)
                orygText = notepadItem.getText();
        } else if (openOrSave == save) {
            notepadModel.AddText(title(), notepadItem.getText());
        }
    }

    public void Open() throws Exception {

        boolean open = true;

        if(open == open) {
            notepadItem = notepadModel.getItemByTitle(title());
            if(notepadItem != null)
                orygText = notepadItem.getText();
        }

        if (textArea == null)
            return;

        try {

            OpenOrSaveDialog(true);
            if(notepadItem != null) {
                textArea.setText(notepadItem.getText());
                titleField.setText(notepadItem.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Save(Boolean showDialog) throws Exception {
        boolean save = true;

        if (save == save) {
            notepadModel.AddText(title(), notepadItem.getText());
        }
        if (textArea == null)
            return;

        if (notepadItem == null)
            System.out.println("SelectedItem is null (method Save in NotepadOperationsWithDatabase)");

        String content;
        content = textArea.getText();
        if (notepadItem != null) {
            notepadItem.setText(content);
            notepadItem.setTitle(title());
        }

        try {
            if (notepadItem != null) {
                if (notepadModel.Exist(title()))
                    notepadModel.Update(title(), notepadItem.getText());
                else
                    notepadModel.AddText(title(), notepadItem.getText());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        private void SaveNew() {
            if (textArea == null)
                return;

            try {

                if (notepadItem != null) {
                    notepadItem.setTitle(title());
                    notepadItem.setText(textArea.getText());
                    notepadModel.AddText(title(), notepadItem.getText());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void Cut() {
        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String cutString = textArea.getSelectedText();
        StringSelection cutSelection = new StringSelection(cutString);
        clipboard.setContents(cutSelection, cutSelection);
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());

    }

    public void Copy() {

        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        String copyText = textArea.getSelectedText();
        StringSelection copySelection = new StringSelection(copyText);
        clipboard.setContents(copySelection, copySelection);
    }


    public void Paste() {
        Clipboard clipboard;
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
            Transferable pasteText = clipboard.getContents(textArea);
            String str = (String) pasteText.getTransferData(DataFlavor.stringFlavor);
            textArea.replaceRange(str, textArea.getSelectionStart(), textArea.getSelectionEnd());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SelectAll() {
        textArea.selectAll();
    }


    class myHighlighter extends DefaultHighlighter.DefaultHighlightPainter {
        public  myHighlighter(Color color) {
            super(color);
        }
    }

    Highlighter.HighlightPainter highlightPainter = new NotesOperationsWithDatabase.myHighlighter(Color.yellow);


    public void removeHighLight(JTextComponent textComponent){
        Highlighter removeHighlighter = textComponent.getHighlighter();
        Highlighter.Highlight[] remove = removeHighlighter.getHighlights();

        for(int i = 0; i<remove.length; i++){
            if(remove[i].getPainter() instanceof NotesOperationsWithDatabase.myHighlighter){
                removeHighlighter.removeHighlight(remove[i]);
            }
        }
    }
    public void Search(JTextComponent textComponent, String textString){
        removeHighLight(textComponent);

        try{
            Highlighter highlighter = textComponent.getHighlighter();
            Document doc = textComponent.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            while((pos = text.toUpperCase().indexOf(textString.toUpperCase(), pos)) >= 0) {
                highlighter.addHighlight(pos, pos+textString.length(), highlightPainter);
                pos += textString.length();
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
