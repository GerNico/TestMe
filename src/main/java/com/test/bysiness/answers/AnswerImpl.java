package com.test.bysiness.answers;

import com.test.bysiness.Answer;

import java.util.Objects;

public class AnswerImpl<T> implements Answer<T> {
    private T value;

    public AnswerImpl(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerImpl<?> that = (AnswerImpl<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
