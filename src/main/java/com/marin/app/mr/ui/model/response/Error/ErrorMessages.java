package com.marin.app.mr.ui.model.response.Error;

/**
 * Enum class for custom error messages.
 */
public enum ErrorMessages {

    CLIENTS_INVALID_ADDRESS("Client is not living at the provided address."),
    READING_ALREADY_EXISTS("Reading for provided address and date already exists in database.");


    private String errorMessage;

    ErrorMessages (String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
