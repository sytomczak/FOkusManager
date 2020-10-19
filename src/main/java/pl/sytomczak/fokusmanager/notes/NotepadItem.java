package pl.sytomczak.fokusmanager.notes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NotepadItem {
    public String getText() {
        return text.get();
    }
    public String getTitle() {return title.get(); }

    public StringProperty textProperty() {
        return text;
    }
    public StringProperty titleProperty() {return title; }

    public void setText(String text) {
        this.text.set(text);
    }

    public void setTitle(String title){
        this.title.set(title);
    }

    private StringProperty text;
    private StringProperty title;


    public NotepadItem()
    {
        this.title = new SimpleStringProperty("");
        this.text = new SimpleStringProperty("");
    }

    public NotepadItem(String text) {
        this.setText(text);
    }


    public NotepadItem(String title, String text)
    {
        this.title = new SimpleStringProperty(title);
        this.text = new SimpleStringProperty(text);
    }
}
