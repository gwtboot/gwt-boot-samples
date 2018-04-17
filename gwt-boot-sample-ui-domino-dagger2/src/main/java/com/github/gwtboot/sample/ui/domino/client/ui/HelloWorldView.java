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

@Singleton
public class HelloWorldView {

	private static Logger logger = Logger
			.getLogger(HelloWorldView.class.getName());

	public HelloWorldView() {
		Layout layout = Layout.create("TODO-List").removeLeftPanel()
				.show(Theme.BLUE);

		TextBox title = TextBox.create("Title").floating();
		TextArea description = TextArea.create("Description").floating()
				.setRows(1);

		ListGroup<TodoItem> todoItemsGroup = ListGroup.create();
		ListGroup<TodoItem> doneItemsGroup = ListGroup.create();

		Button addButton = Button.createPrimary("ADD");
		addButton.asElement().style.setProperty("min-width", "120px");

		addButton.addClickListener(evt -> {
			if (!title.isEmpty() && !description.isEmpty()) {
				TodoItem todoItem = new TodoItem(title.getValue(),
						description.getValue());

				ListItem<TodoItem> listItem = todoItemsGroup
						.createItem(todoItem, todoItem.description)
						.setHeading(todoItem.title);

				IconButton doneButton = IconButton.create(Icons.ALL.check());
				doneButton.addClickListener(clickEvent -> {
					clickEvent.stopPropagation();
					todoItemsGroup.removeItem(listItem);
					doneItemsGroup.appendItem(listItem);
					doneButton.asElement().remove();
				});

				doneButton.setButtonType(StyleType.SUCCESS);
				doneButton.asElement().style
						.setProperty("position", "absolute");
				doneButton.asElement().style.setProperty("top", "10px");
				doneButton.asElement().style.setProperty("right", "10px");

				listItem.appendContent(doneButton.asElement());

				todoItemsGroup.appendItem(listItem);

				Tooltip.create(doneButton.asElement(), "Mark Done");
			}
		});

		layout.getContentPanel().appendChild(
				Card.create("NEW TODO", "Add a new todo list item")
						.appendContent(title.asElement())
						.appendContent(description.asElement())
						.appendContent(addButton.asElement()).asElement());

		layout.getContentPanel().appendChild(Card.create("TODO ITEMS")
				.appendContent(todoItemsGroup.asElement()).asElement());

		layout.getContentPanel().appendChild(Card.create("DONE ITEMS")
				.appendContent(doneItemsGroup.asElement()).asElement());
	}

	private class TodoItem {
		private String title;
		private String description;

		public TodoItem(String title, String description) {
			this.title = title;
			this.description = description;
		}
	}
}
