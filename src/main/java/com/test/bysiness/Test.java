package com.test.bysiness;

import com.test.bysiness.questions.Question;

import java.util.List;
import java.util.Objects;

public class Test {
    private Integer id;
    private List<Question> questionList;

    public Test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(questionList, test.questionList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, questionList);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", questionList=" + questionList +
                '}';
    }
}
