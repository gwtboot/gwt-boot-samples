package com.github.gwtboot.sample.ui.vuegwt.client.components.todolist;

/**
 * Simple Todo model
 */
public class Todo
{
    private String text;
    private boolean isDone;

    public Todo(String text)
    {
        this.text = text;
        this.isDone = false;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public boolean isDone()
    {
        return isDone;
    }

    public void setDone(boolean done)
    {
        isDone = done;
    }
}
