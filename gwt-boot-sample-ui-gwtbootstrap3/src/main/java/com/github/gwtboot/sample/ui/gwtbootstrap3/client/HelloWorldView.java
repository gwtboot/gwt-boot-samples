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
package com.github.gwtboot.sample.ui.gwtbootstrap3.client;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HelloWorldView extends Composite {

	private static Logger logger = Logger
			.getLogger(HelloWorldView.class.getName());

	interface HelloWorldViewUiBinder extends UiBinder<Widget, HelloWorldView> {
	}

	private static HelloWorldViewUiBinder uiBinder = GWT
			.create(HelloWorldViewUiBinder.class);

	@UiField
	Button showButton;

	@UiField
	MultipleSelect foodMultipleSelect;

	public HelloWorldView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("showButton")
	void handleShowButtonClick(ClickEvent e) {
		foodMultipleSelect.getSelectedItems()
				.forEach(item -> Notify.notify(item.toString()));
	}
}
