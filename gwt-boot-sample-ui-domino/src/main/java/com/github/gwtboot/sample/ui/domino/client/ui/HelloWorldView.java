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

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.BUNDLE;
import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;
import static org.jboss.elemento.Elements.div;

import java.util.logging.Logger;

import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.FieldsGrouping;
import org.dominokit.domino.ui.forms.Select;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;
import org.jboss.elemento.IsElement;

import com.github.gwtboot.sample.ui.domino.client.ui.TodoItem.Priority;

import elemental2.dom.HTMLDivElement;

public class HelloWorldView implements IsElement<HTMLDivElement> {

    private static Logger logger = Logger
            .getLogger(HelloWorldView.class.getName());

    private HTMLDivElement root = div().css(BUNDLE.css().contentMargin()).element();

    private TextBox titleTextBox;

    private TextArea descriptionTextArea;

    private ListGroup<TodoItem> todoItemsListGroup;

    private ListGroup<TodoItem> doneItemsListGroup;

    private Select<Priority> prioritySelect;

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

        this.prioritySelect = Select.ofEnum(CONSTANTS.priority(), Priority.values())
                .groupBy(fieldsGrouping)
                .setRequired(true)
                .setAutoValidation(true);

        this.todoItemsListGroup = ListGroup.<TodoItem>create()
                .setSelectable(false)
                .setItemRenderer((listGroup, listItem) -> {
                    listItem
                            .css(Styles.padding_10)
                            .appendChild(FlexLayout.create()
                                    .setJustifyContent(FlexJustifyContent.SPACE_AROUND)
                                    .appendChild(FlexItem.create()
                                            .setFlexGrow(1)
                                            .appendChild(BlockHeader
                                                    .create(listItem.getValue().getTitle(), listItem.getValue().getDescription())
                                                    .css(Styles.m_b_0)
                                            )
                                    )
                                    .appendChild(FlexItem.create()
                                            .css(Styles.m_l_10, Styles.m_r_10, Styles.m_t_10)
                                            .appendChild(priorityBadge(listItem.getValue().getPriority()))
                                    )
                                    .appendChild(FlexItem.create()
                                            .appendChild(Icons.ALL.check_bold_mdi()
                                                    .setColor(Color.GREEN)
                                                    .clickable()
                                                    .addClickListener(evt -> complete(listItem.getValue()))
                                            ))
                            );
                });

        this.doneItemsListGroup = ListGroup.<TodoItem>create()
                .setSelectable(false)
                .setItemRenderer((listGroup, listItem) -> {
                    listItem
                            .css(Styles.padding_10)
                            .appendChild(FlexLayout.create()
                                    .setJustifyContent(FlexJustifyContent.SPACE_AROUND)
                                    .appendChild(FlexItem.create()
                                            .setFlexGrow(1)
                                            .appendChild(BlockHeader
                                                    .create(listItem.getValue().getTitle(), listItem.getValue().getDescription())
                                                    .css(Styles.m_b_0)
                                            )
                                    )
                                    .appendChild(FlexItem.create()
                                            .css(Styles.m_l_10, Styles.m_r_10, Styles.m_t_10)
                                            .appendChild(priorityBadge(listItem.getValue().getPriority()))
                                    )
                            );
                });

        this.addButton = Button.createPrimary(CONSTANTS.add())
                .styler(style -> style.add(BUNDLE.css().addButton()))
                .addClickListener(evt -> onAddButtonClick());

        root.appendChild(Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo())
                .appendChild(titleTextBox)
                .appendChild(descriptionTextArea)
                .appendChild(prioritySelect)
                .appendChild(addButton)
                .element());

        root.appendChild(Card.create(CONSTANTS.todo_items())
                .appendChild(todoItemsListGroup)
                .element());

        root.appendChild(Card.create(CONSTANTS.done_items())
                .appendChild(doneItemsListGroup)
                .element());
    }

    void onAddButtonClick() {
        if (fieldsGrouping.validate().isValid()) {
            TodoItem todoItem = new TodoItem(
                    titleTextBox.getValue(),
                    descriptionTextArea.getValue(),
                    prioritySelect.getValue()
            );

            todoItemsListGroup.addItem(todoItem);

            fieldsGrouping
                    .clear()
                    .clearInvalid();
        }
    }

    private Badge priorityBadge(Priority priority) {
        switch (priority) {
            case High:
                return Badge.create("High")
                        .setBackground(Color.RED);
            case Medium:
                return Badge.create("Medium")
                        .setBackground(Color.ORANGE);
            case Low:
            default:
                return Badge.create("Low")
                        .setBackground(Color.TEAL);
        }

    }

    void complete(TodoItem todoItem) {
        todoItemsListGroup.removeItem(todoItem);
        doneItemsListGroup.addItem(todoItem);
    }

    @Override
    public HTMLDivElement element() {
        return root;
    }
}
