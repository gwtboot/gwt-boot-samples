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

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.IconButton;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.*;
import org.dominokit.domino.ui.icons.Icon;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Style;
import org.dominokit.domino.ui.style.Styles;
import org.jboss.gwt.elemento.core.IsElement;

import java.util.logging.Logger;

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.BUNDLE;
import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;
import static org.jboss.gwt.elemento.core.Elements.div;

public class HelloWorldView implements IsElement<HTMLDivElement> {

    private static Logger logger = Logger
            .getLogger(HelloWorldView.class.getName());

    private HTMLDivElement root = div().css(BUNDLE.css().contentMargin()).asElement();

    private TextBox titleTextBox;

    private TextArea descriptionTextArea;

    private ListGroup<TodoItem> todoItemsListGroup;

    private ListGroup<TodoItem> doneItemsListGroup;

    private Select<String> prioritySelect;

    private Button addButton;

    private FieldsGrouping fieldsGrouping;

    public HelloWorldView() {
        logger.info("Create HelloWorldView");

        fieldsGrouping = FieldsGrouping.create();

        this.titleTextBox = TextBox.create(CONSTANTS.title())
                .groupBy(fieldsGrouping)
                .setRequired(true)
                .setAutoValidation(true);

        this.descriptionTextArea = TextArea.create(CONSTANTS.description())
                .groupBy(fieldsGrouping)
                .setRequired(true)
                .setAutoValidation(true)
                .setRows(1);

        this.prioritySelect = Select.<String>create(CONSTANTS.priority())
                .groupBy(fieldsGrouping)
                .setRequired(true)
                .setAutoValidation(true)
                .addOption(SelectOption.create("High", "High"))
                .addOption(SelectOption.create("Medium", "Medium"))
                .addOption(SelectOption.create("Low", "Low"));

        this.todoItemsListGroup = ListGroup.<TodoItem>create()
                .setSelectable(false);
        this.doneItemsListGroup = ListGroup.<TodoItem>create()
                .setSelectable(false);

        this.addButton = Style.of(Button.createPrimary(CONSTANTS.add()))
                .css(BUNDLE.css().addButton())
                .get()
                .addClickListener(evt -> onAddButtonClick());

        root.appendChild(Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo())
                .appendChild(titleTextBox)
                .appendChild(descriptionTextArea)
                .appendChild(prioritySelect)
                .appendContent(addButton)
                .asElement());

        root.appendChild(Card.create(CONSTANTS.todo_items())
                .appendChild(todoItemsListGroup)
                .asElement());

        root.appendChild(Card.create(CONSTANTS.done_items())
                .appendContent(doneItemsListGroup)
                .asElement());

    }


    void onAddButtonClick() {
        // We click the addButton
        if (fieldsGrouping.validate().isValid()) {
            // Create a new todoItem
            TodoItem todoItem = new TodoItem(titleTextBox.getValue(),
                    descriptionTextArea.getValue());

            ListItem<TodoItem> listItem = todoItemsListGroup
                    .createItem(todoItem, todoItem.getDescription())
                    .setHeading(todoItem.getTitle());


            IconButton doneButton = IconButton.create(Icons.ALL.check())
                    .linkify();

            Style.of(doneButton)
                    .css(Styles.pull_right, BUNDLE.css().doneButton())
                    .get()
                    .setColor(Color.GREEN)
                    .addClickListener(evt -> {
                        onDoneButtonClick(listItem);
                        doneButton.asElement().remove();
                    });


            listItem.getBody().appendChild(doneButton.asElement());
            listItem.getBody().appendChild(createPriorityBadge().asElement());

            todoItemsListGroup.appendItem(listItem);

            //clear all fields
            fieldsGrouping
                    .clear()
                    .clearInvalid();
        }
    }

    private Badge createPriorityBadge() {
        if ("High".equals(prioritySelect.getValue())) {
            return Style.of(Badge.create("High"))
                    .css(Styles.pull_right)
                    .get()
                    .setBackground(Color.RED);
        } else if ("Medium".equals(prioritySelect.getValue())) {
            return Style.of(Badge.create("Medium"))
                    .css(Styles.pull_right)
                    .get().setBackground(Color.ORANGE);
        } else {
            return Style.of(Badge.create("Low"))
                    .css(Styles.pull_right)
                    .get().setBackground(Color.TEAL);
        }
    }

    void onDoneButtonClick(ListItem<TodoItem> listItem) {
        // We click the doneButton
        todoItemsListGroup.removeItem(listItem);
        doneItemsListGroup.appendItem(listItem);
    }

    @Override
    public HTMLDivElement asElement() {
        return root;
    }
}
