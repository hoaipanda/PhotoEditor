package com.example.dell.photoeditor.Data;

/**
 * Created by dell on 4/19/2018.
 */

public class Filter {
    private int icon;
    private String name;

    public Filter() {
    }

    public Filter(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
