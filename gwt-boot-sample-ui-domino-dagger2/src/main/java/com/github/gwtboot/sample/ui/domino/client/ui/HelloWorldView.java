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

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.IconButton;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.domino.ui.popover.Tooltip;
import org.dominokit.domino.ui.style.StyleType;
import org.dominokit.domino.ui.themes.Theme;

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.BUNDLE;
import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;

@Singleton
public class HelloWorldView {

	private static Logger logger = Logger
			.getLogger(HelloWorldView.class.getName());

	@Inject
	public HelloWorldView() {
		logger.info("Create HelloWorldView.");

		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel().show(Theme.BLUE);

		TextBox title = TextBox.create(CONSTANTS.title()).floating();
		TextArea description = TextArea.create(CONSTANTS.description()).floating().setRows(1);

		ListGroup<TodoItem> todoItemsGroup = ListGroup.create();
		ListGroup<TodoItem> doneItemsGroup = ListGroup.create();

		Button addButton = Button.createPrimary(CONSTANTS.add());
		addButton.asElement().classList.add(BUNDLE.css().addButton());

		addButton.addClickListener(evt -> {
			if (!title.isEmpty() && !description.isEmpty()) {
				TodoItem todoItem = new TodoItem(title.getValue(), description.getValue());

				ListItem<TodoItem> listItem = todoItemsGroup.createItem(todoItem, todoItem.getDescription())
						.setHeading(todoItem.getTitle());

				IconButton doneButton = IconButton.create(Icons.ALL.check());
				doneButton.addClickListener(clickEvent -> {
					clickEvent.stopPropagation();
					todoItemsGroup.removeItem(listItem);
					doneItemsGroup.appendItem(listItem);
					doneButton.asElement().remove();
				});

				doneButton.setButtonType(StyleType.SUCCESS);
				doneButton.asElement().classList.add(BUNDLE.css().doneButton());

				listItem.appendContent(doneButton.asElement());

				todoItemsGroup.appendItem(listItem);

				Tooltip.create(doneButton.asElement(), CONSTANTS.mark_done());
			}
		});

		layout.getContentPanel().appendChild(
				Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo()).appendContent(title.asElement())
						.appendContent(description.asElement()).appendContent(addButton.asElement()).asElement());

		layout.getContentPanel().appendChild(Card.create(CONSTANTS.todo_items()).appendContent(todoItemsGroup.asElement()).asElement());

		layout.getContentPanel().appendChild(Card.create(CONSTANTS.done_items()).appendContent(doneItemsGroup.asElement()).asElement());
	}

}
