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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.forms.TextArea;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.lists.ListGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Matchers;
import org.mockito.Mock;

import com.google.gwtmockito.GwtMockitoTestRunner;

@RunWith(GwtMockitoTestRunner.class)
public class HelloWorldCompositeTest {

	private HelloWorldComposite helloWorldComposite;

	@Mock
	private TextBox titleTextBox;

	@Mock
	private TextArea descriptionTextArea;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private ListGroup<TodoItem> todoItemsListGroup;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private ListGroup<TodoItem> doneItemsListGroup;

	@Mock
	private Button addButton;

	@Mock
	private Layout layout;

	@Mock
	private TodoItemRenderer toDoItemRenderer;

	@Before
	public void setUp() throws Exception {
		helloWorldComposite = new HelloWorldComposite(titleTextBox, descriptionTextArea, todoItemsListGroup, doneItemsListGroup,
				toDoItemRenderer, addButton, layout);
		helloWorldComposite = spy(helloWorldComposite);
	}

	@Test
	public void testHandleAddButtonClickTitleAndDescriptionNotEmpty() throws Exception {
		// Prepare
		doReturn("Title").when(titleTextBox).getValue();
		doReturn("Description").when(descriptionTextArea).getValue();

		// CUT
		helloWorldComposite.handleAddButtonClick();

		// Asserts
		verify(todoItemsListGroup, times(1)).addItem(Matchers.<TodoItem>any());
		verify(titleTextBox, times(1)).setValue("");
		verify(descriptionTextArea, times(1)).setValue("");
	}

}