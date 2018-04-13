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

import org.gwtbootstrap3.extras.notify.client.ui.Notify;

import com.github.gwtboot.sample.collection.client.event.ChangeViewEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

@Singleton
public class HelloWorldViewEventHandler {

	private static Logger logger = Logger
			.getLogger(HelloWorldViewEventHandler.class.getName());

	// GWT EventBinder
	interface HelloWorldViewEventHandlerEventBinder
			extends EventBinder<HelloWorldViewEventHandler> {
	}

	private final HelloWorldViewEventHandlerEventBinder eventBinder = GWT
			.create(HelloWorldViewEventHandlerEventBinder.class);

	@Inject
	public HelloWorldViewEventHandler(EventBus eventBus) {
		eventBinder.bindEventHandlers(this, eventBus);
	}

	@EventHandler
	void onChangeViewed(ChangeViewEvent event) {
		logger.info("ChangeViewEvent triggered: " + event.getWidgetName()
				+ " - Source: " + event.getSource());
		Notify.notify("ChangeViewEvent triggered: " + event.getWidgetName()
				+ " - Source: " + event.getSource());
	}

}
