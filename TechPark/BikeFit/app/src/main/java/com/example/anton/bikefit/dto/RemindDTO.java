package com.example.anton.bikefit.dto;

public class RemindDTO {
    private String title;

    public RemindDTO(String titile) {
        this.title = titile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titile) {
        this.title = titile;
    }
}
