/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.gwtboot.sample.ui.domino.client.ui;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.popover.Tooltip;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.logging.Logger;

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;

@Singleton
public class HelloWorldView {

    private static Logger logger = Logger
            .getLogger(HelloWorldView.class.getName());

    TextBox titleTextBox;

    TextArea descriptionTextArea;

    ListGroup<TodoItem> todoItemsListGroup;

    ListGroup<TodoItem> doneItemsListGroup;

    Button addButton;

    Layout layout;

    @Inject
    public HelloWorldView(TextBox titleTextBox, TextArea descriptionTextArea,
                          @Named("todoItemsListGroup") ListGroup<TodoItem> todoItemsListGroup,
                          @Named("doneItemsListGroup") ListGroup<TodoItem> doneItemsListGroup,
                          @Named("todoItemRenderer") ToDoItemRenderer toDoItemRenderer,
                          Button addButton, Layout layout) {
        logger.info("Create HelloWorldView");

        this.titleTextBox = titleTextBox;
        this.descriptionTextArea = descriptionTextArea;
        this.todoItemsListGroup = todoItemsListGroup;
        this.doneItemsListGroup = doneItemsListGroup;
        this.addButton = addButton;
        this.layout = layout;

        toDoItemRenderer.setOnCheckHandler(this::handleCheckOkClick);
        this.todoItemsListGroup.setItemRenderer(toDoItemRenderer);

        logger.info("Button: " + addButton.toString());

        // Add button and listener
        this.addButton.addClickListener(addButtonClickEvent -> {
            handleAddButtonClick();
        });
    }

    void handleAddButtonClick() {
        if (!titleTextBox.isEmpty() && !descriptionTextArea.isEmpty()) {
            TodoItem todoItem = new TodoItem(titleTextBox.getValue(),
                    descriptionTextArea.getValue());

            todoItemsListGroup.addItem(todoItem);

            titleTextBox.setValue("");
            descriptionTextArea.setValue("");
        }
    }

    Tooltip tooltip(Button doneButton) {
        return Tooltip.create(doneButton.element(), CONSTANTS.mark_done());
    }

    void handleCheckOkClick(TodoItem todoItem) {
        todoItemsListGroup.removeItem(todoItem);
        doneItemsListGroup.addItem(todoItem);
    }

}
