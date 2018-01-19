package com.test.bysiness;

import java.io.Serializable;

public interface Answer<T> extends Duplicate<Answer<T>> {
    T get();
}
