package com.example.anton.headfirst_6_lists;

public class Drink {
    private String name;
    private String discription;
    private int imgResId;


    private final static Drink[] drinks={
        new Drink("Latte","A couple of espresso shots with steamed milk",R.drawable.latte),
        new Drink("Cappoucino","Espresso, hot milk, and a steamed milk foam",R.drawable.latte),
        new Drink("Filter", "Highest quality beans roasted and brewed fresh",R.drawable.latte),
    };

    private Drink(String name, String discription, int imgResId){
        this.discription=discription;
        this.imgResId=imgResId;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public int getImgResId() {
        return imgResId;
    }

    public static Drink[] getDrinks() {
        return drinks;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
