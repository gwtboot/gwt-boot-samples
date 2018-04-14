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

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.extras.notify.client.ui.Notify;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;

import com.github.gwtboot.sample.collection.client.event.ChangeViewEvent;
import com.github.gwtboot.sample.collection.client.extra.Banana;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@Singleton
public class HelloWorldView extends Composite {

	private static Logger logger = Logger
			.getLogger(HelloWorldView.class.getName());

	// UI Binder
	interface HelloWorldViewUiBinder extends UiBinder<Widget, HelloWorldView> {
	}

	private static HelloWorldViewUiBinder uiBinder = GWT
			.create(HelloWorldViewUiBinder.class);

	@UiField
	Button showButton;

	@UiField
	MultipleSelect foodMultipleSelect;

	private Banana banana;

	private int bananaCount;

	private final EventBus eventBus;

	@Inject
	public HelloWorldView(Banana banana, EventBus eventBus,
			HelloWorldViewEventHandler helloWorldViewEventHandler) {
		logger.info("HelloWorldView creating.");
		this.banana = banana;
		this.eventBus = eventBus;

		// UI Binder
		initWidget(uiBinder.createAndBindUi(this));

		// Call banana three times
		for (int i = 0; i < 3; i++) {
			callBanana();
		}

		logger.info("HelloWorldView created.");
	}

	public void setBananaCount(int bananaCount) {
		this.bananaCount = bananaCount;
	}

	@UiHandler("showButton")
	void handleShowButtonClick(ClickEvent e) {
		if (bananaCount != 0) {
			foodMultipleSelect.getSelectedItems()
					.forEach(item -> Notify.notify(item.toString()));
		} else {
			notify("No banana, no food.");
		}

		eventBus.fireEvent(new ChangeViewEvent("showButton"));
	}

	void notify(String s) {
		Notify.notify(s);
	}

	private void callBanana() {
		notify("Calling Banana.");
		if (bananaCount == 0) {
			notify("Banana Sum the first: " + banana.sum());
			bananaCount++;
		} else if (bananaCount == 1) {
			banana.x = 10;
			banana.y = 10;
			notify("Banana Sum the second: " + banana.sum());
			bananaCount++;
		} else {
			banana.x = 40;
			banana.y = 40;
			notify("Banana Sum the third: " + banana.sum());
		}
	}

}
