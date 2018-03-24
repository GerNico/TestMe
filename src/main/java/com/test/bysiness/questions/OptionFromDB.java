package com.test.bysiness.questions;

import java.util.Objects;

public class OptionFromDB {
    private Integer id;
    private String text;
    private String isCorrect;
    private String isSequenceBased;
    private Integer numberInSequence;

    public OptionFromDB() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getIsSequenceBased() {
        return isSequenceBased;
    }

    public void setIsSequenceBased(String isSequenceBased) {
        this.isSequenceBased = isSequenceBased;
    }

    public Integer getNumberInSequence() {
        return numberInSequence;
    }

    public void setNumberInSequence(Integer numberInSequence) {
        this.numberInSequence = numberInSequence;
    }

    @Override
    public String toString() {
        return "OptionFromDB{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCorrect='" + isCorrect + '\'' +
                ", isSequenceBased='" + isSequenceBased + '\'' +
                ", numberInSequence=" + numberInSequence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionFromDB that = (OptionFromDB) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(text, that.text) &&
                Objects.equals(isCorrect, that.isCorrect) &&
                Objects.equals(isSequenceBased, that.isSequenceBased) &&
                Objects.equals(numberInSequence, that.numberInSequence);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, text, isCorrect, isSequenceBased, numberInSequence);
    }
}
