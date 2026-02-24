package com.gustcustodio.to_do_list_api.services.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }

}
