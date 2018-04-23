package com.github.gwtboot.sample.ui.domino.client.ui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface TodoClientBundle extends ClientBundle {

    TodoClientBundle BUNDLE=GWT.create(TodoClientBundle.class);
    TodoConstants CONSTANTS=GWT.create(TodoConstants.class);

    interface TodoConstants extends Constants {

        @DefaultStringValue("TODO-List")
        String appTitle();

        @DefaultStringValue("Title")
        String title();

        @DefaultStringValue("Description")
        String description();

        @DefaultStringValue("ADD")
        String add();

        @DefaultStringValue("Mark Done")
        String mark_done();

        @DefaultStringValue("NEW TODO")
        String new_todo();

        @DefaultStringValue("Add a new todo list item")
        String add_new_todo();

        @DefaultStringValue("TODO ITEMS")
        String todo_items();

        @DefaultStringValue("DONE ITEMS")
        String done_items();
    }

    interface TodoStyles extends CssResource {

        String addButton();

        String doneButton();
    }

    @Source("todo.gss")
    TodoStyles css();
}
