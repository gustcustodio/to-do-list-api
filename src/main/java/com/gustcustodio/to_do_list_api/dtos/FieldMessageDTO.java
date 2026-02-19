package com.gustcustodio.to_do_list_api.dtos;

public class FieldMessageDTO {

    private String fieldName;
    private String message;

    public FieldMessageDTO() {
    }

    public FieldMessageDTO(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
