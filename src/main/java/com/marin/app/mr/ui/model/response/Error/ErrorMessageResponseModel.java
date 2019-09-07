package com.marin.app.mr.ui.model.response.Error;

import java.time.LocalDateTime;

public class ErrorMessageResponseModel {

    private LocalDateTime date;
    private String message;

    public ErrorMessageResponseModel() {
    }

    public ErrorMessageResponseModel(LocalDateTime date, String message) {
        this.date = date;
        this.message = message;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
