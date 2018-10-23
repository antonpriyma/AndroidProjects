package com.example.anton.rk_1;

import android.content.Intent;

public class Item {

    private String title;

    public Item(Integer title) {
        this.title = title.toString();
    }


    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return title != null ? title.equals(item.title) : item.title == null;
    }

    @Override
    public int hashCode() {
        int result = (title != null ? title.hashCode() : 0);
        return result;
    }
}

