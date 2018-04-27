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
import javax.inject.Named;
import javax.inject.Singleton;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.themes.Theme;

import dagger.Module;
import dagger.Provides;

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.BUNDLE;
import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;

@Module
public class HelloWorldUiBinder {

	private static Logger logger = Logger
			.getLogger(HelloWorldUiBinder.class.getName());

	public HelloWorldUiBinder() {
		logger.info("Create HelloWorldUiBinder");
	}

	@Provides
	@Singleton
	@Inject
	Layout layout(TextBox titleTextBox, TextArea descriptionTextArea,
			@Named("todoItemsListGroup") ListGroup<TodoItem> todoItemsListGroup,
			@Named("doneItemsListGroup") ListGroup<TodoItem> doneItemsListGroup,
			Button addButton) {
		Layout layout = Layout.create(CONSTANTS.appTitle()).removeLeftPanel()
				.show(Theme.BLUE);
		layout.getContentPanel().appendChild(
				Card.create(CONSTANTS.new_todo(), CONSTANTS.add_new_todo())
						.appendContent(titleTextBox.asElement())
						.appendContent(descriptionTextArea.asElement())
						.appendContent(addButton.asElement()).asElement());
		layout.getContentPanel().appendChild(Card.create(CONSTANTS.todo_items())
				.appendContent(todoItemsListGroup.asElement()).asElement());
		layout.getContentPanel().appendChild(Card.create(CONSTANTS.done_items())
				.appendContent(doneItemsListGroup.asElement()).asElement());

		logger.info("Button: " + addButton.toString());

		return layout;
	}

	@Provides
	@Singleton
	TextBox titleTextBox() {
		return TextBox.create(CONSTANTS.title()).floating();
	}

	@Provides
	@Singleton
	TextArea descriptionTextArea() {
		return TextArea.create(CONSTANTS.description()).floating().setRows(1);
	}

	@Named("todoItemsListGroup")
	@Provides
	@Singleton
	ListGroup<TodoItem> todoItemsListGroup() {
		return ListGroup.create();
	}

	@Named("doneItemsListGroup")
	@Provides
	@Singleton
	ListGroup<TodoItem> doneItemsListGroup() {
		return ListGroup.create();
	}

	@Provides
	@Singleton
	Button addButton() {
		Button addButton = Button.createPrimary(CONSTANTS.add());
		addButton.asElement().classList.add(BUNDLE.css().addButton());
		return addButton;
	}

}
