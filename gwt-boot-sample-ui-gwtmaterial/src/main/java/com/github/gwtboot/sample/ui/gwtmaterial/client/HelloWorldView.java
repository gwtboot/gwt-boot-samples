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
package com.github.gwtboot.sample.ui.gwtmaterial.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;

public class HelloWorldView extends Composite {

	private static Logger logger = Logger
			.getLogger(HelloWorldView.class.getName());

	interface HelloWorldViewUiBinder extends UiBinder<Widget, HelloWorldView> {
	}

	private static HelloWorldViewUiBinder uiBinder = GWT
			.create(HelloWorldViewUiBinder.class);

	@UiField
	MaterialButton showButton;

	@UiField
	MaterialDropDown<?> foodDropDown;

	public HelloWorldView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("showButton")
	void handleShowButtonClick(ClickEvent e) {
		foodDropDown.getItems().forEach(item -> {
			MaterialToast.fireToast(item.toString());
			logger.info(item.toString());
		});
	}

	@UiHandler("foodDropDown")
	void handleDropdown(SelectionEvent<Widget> callback) {
		MaterialToast.fireToast(
				"Selected : " + ((MaterialLink) callback.getSelectedItem())
						.getText());
	}
}
