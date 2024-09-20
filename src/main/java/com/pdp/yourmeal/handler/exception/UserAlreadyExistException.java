package com.pdp.yourmeal.handler.exception;

import java.io.Serial;
import java.text.MessageFormat;

/**
 * @author Aliabbos Ashurov
 * @since 19/September/2024  21:01
 **/
public class UserAlreadyExistException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
