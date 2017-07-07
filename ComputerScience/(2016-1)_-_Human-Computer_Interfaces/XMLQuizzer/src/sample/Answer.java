package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by jvarred on 6/11/16.
 */
public class Answer {
    private final SimpleStringProperty text;
    private final SimpleBooleanProperty correct;
    private final SimpleIntegerProperty id;

    public Answer(String text, Boolean correct, Integer id) {
        this.text = new SimpleStringProperty(text);
        this.correct = new SimpleBooleanProperty(correct);
        this.id = new SimpleIntegerProperty(id);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public boolean getCorrect() {
        return correct.get();
    }

    public SimpleBooleanProperty correctProperty() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct.set(correct);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}