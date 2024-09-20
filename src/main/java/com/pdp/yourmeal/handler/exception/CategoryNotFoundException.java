package com.pdp.yourmeal.handler.exception;

import java.io.Serial;
import java.text.MessageFormat;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  21:04
 **/
public class CategoryNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
