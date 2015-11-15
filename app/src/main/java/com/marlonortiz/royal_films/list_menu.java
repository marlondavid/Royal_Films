package com.marlonortiz.royal_films;

/**
 * Created by Marlangas on 11/14/2015.
 */
public class list_menu {
    private String name;
    private int imageid;

    public list_menu(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}


