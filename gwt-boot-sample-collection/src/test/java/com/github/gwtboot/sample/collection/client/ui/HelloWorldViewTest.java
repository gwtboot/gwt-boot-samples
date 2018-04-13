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
package com.github.gwtboot.sample.collection.client.ui;

import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.github.gwtboot.sample.collection.client.Banana;
import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class HelloWorldViewTest {

	@Mock
	private Banana banana;

	@GwtMock
	private MultipleSelect foodMultipleSelect;

	private HelloWorldView view;

	@Mock
	private EventBus eventBus;

	@Mock
	private HelloWorldViewEventHandler helloWorldViewEventHandler;

	@Before
	public void setUp() throws Exception {
		view = new HelloWorldView(banana, eventBus, helloWorldViewEventHandler);
		view = spy(view);
	}

	@Test
	public void handleShowButtonClickWithBananaCountGreaterThanZero()
			throws Exception {
		// CUT
		view.handleShowButtonClick(null);

		// Asserts
		verify(foodMultipleSelect, times(1)).getSelectedItems();
	}

	@Test
	public void handleShowButtonClickWithBananaCountEqualsZero()
			throws Exception {
		// Prepare
		view.setBananaCount(0);
		doNothing().when(view).notify(anyString());

		// CUT
		view.handleShowButtonClick(null);

		// Asserts
		verify(foodMultipleSelect, times(0)).getSelectedItems();
		verify(view, times(1)).notify(anyString());
	}

}