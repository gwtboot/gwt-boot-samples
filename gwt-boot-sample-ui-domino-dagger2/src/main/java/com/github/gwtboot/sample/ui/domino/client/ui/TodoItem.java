package com.github.gwtboot.sample.ui.domino.client.ui;

import java.io.Serializable;

public class TodoItem implements Serializable {

    private String title;
    private String description;

    public TodoItem() {
    }

    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
