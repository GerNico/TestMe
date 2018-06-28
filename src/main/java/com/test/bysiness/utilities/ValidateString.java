package com.test.bysiness.utilities;

import javax.validation.Payload;

public @interface ValidateString {
    String[] acceptedValues();

    String message() default "{validator.ValidateString.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
