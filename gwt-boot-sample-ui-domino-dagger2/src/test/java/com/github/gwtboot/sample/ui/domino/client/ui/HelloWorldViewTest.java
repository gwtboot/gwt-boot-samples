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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.popover.Tooltip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Matchers;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class HelloWorldViewTest {

	private HelloWorldView helloWorldView;

	@Mock
	TextBox titleTextBox;

	@Mock
	TextArea descriptionTextArea;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	ListGroup<TodoItem> todoItemsListGroup;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	ListGroup<TodoItem> doneItemsListGroup;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	Button iconButton;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	Tooltip tooltip;

	@Mock
	Button addButton;

	@Mock
	Layout layout;

	@Before
	public void setUp() throws Exception {
		helloWorldView = new HelloWorldView(titleTextBox, descriptionTextArea, todoItemsListGroup, doneItemsListGroup,
				addButton, layout);
		helloWorldView = spy(helloWorldView);
	}

	@Test
	public void testHandleAddButtonClickTitleAndDescriptionNotEmpty() throws Exception {
		// Prepare
		doReturn("Title").when(titleTextBox).getValue();
		doReturn("Description").when(descriptionTextArea).getValue();
		doReturn(iconButton).when(helloWorldView).doneButton(any());
		doReturn(tooltip).when(helloWorldView).tooltip(iconButton);

		// CUT
		helloWorldView.handleAddButtonClick(null);

		// Asserts
		verify(helloWorldView, times(0)).tooltip(any());
		verify(todoItemsListGroup, times(1)).addItem(Matchers.<TodoItem>any());
	}

}