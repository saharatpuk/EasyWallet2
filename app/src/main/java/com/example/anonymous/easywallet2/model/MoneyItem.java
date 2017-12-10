package com.example.anonymous.easywallet2.model;

/**
 * Created by Anonymous on 10/12/2560.
 */

public class MoneyItem {
    public final int id;
    public final String title;
    public final String number;
    public final String picture;

    public MoneyItem(int id, String title, String number, String picture) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return title;
    }
}
