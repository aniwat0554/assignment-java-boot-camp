package com.example.demo.whiskies.objects;

public class WhiskyDetailResponse {
    private Whisky whiskyDetail;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WhiskyDetailResponse(Whisky whiskyDetail) {
        this.whiskyDetail = whiskyDetail;
    }

    public WhiskyDetailResponse() {
    }

    public Whisky getWhiskyDetail() {
        return whiskyDetail;
    }

    public void setWhiskyDetail(Whisky whiskyDetail) {
        this.whiskyDetail = whiskyDetail;
    }
}
