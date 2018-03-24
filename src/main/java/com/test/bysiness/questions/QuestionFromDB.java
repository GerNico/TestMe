package com.test.bysiness.questions;

import java.util.List;
import java.util.Objects;

class QuestionFromDB {
    private Integer id;
    private String question;
    private String answer;
    private QuestionType type;
    private List<OptionFromDB> optionsList;

    public QuestionFromDB() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<OptionFromDB> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<OptionFromDB> optionsList) {
        this.optionsList = optionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionFromDB that = (QuestionFromDB) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(answer, that.answer) &&
                type == that.type &&
                Objects.equals(optionsList, that.optionsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, question, answer, type, optionsList);
    }

    @Override
    public String toString() {
        return "QuestionFromDB{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", type=" + type +
                ", optionsList=" + optionsList +
                '}';
    }
}
