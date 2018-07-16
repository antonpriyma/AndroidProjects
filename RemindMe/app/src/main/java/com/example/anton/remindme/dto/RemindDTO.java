package com.example.anton.remindme.dto;

public class RemindDTO {
    private String titile;

    public RemindDTO(String titile) {
        this.titile = titile;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }
}
