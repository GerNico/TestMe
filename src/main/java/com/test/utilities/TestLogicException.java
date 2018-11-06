package com.test.utilities;

public class TestLogicException extends RuntimeException {
    public TestLogicException() {
    }

    public TestLogicException(String message) {
        super(message);
    }

    public TestLogicException(Throwable cause) {
        super(cause);
    }
}
