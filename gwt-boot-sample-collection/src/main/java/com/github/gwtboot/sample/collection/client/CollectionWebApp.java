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
package com.github.gwtboot.sample.collection.client;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

@Singleton
public class CollectionWebApp {

	private static Logger logger = Logger
			.getLogger(CollectionWebApp.class.getName());

	private static final String MYFUNCTION_URL = "myfunction.js";

	private Apple apple;

	private int appleCount;

	@Inject
	public CollectionWebApp(Apple apple) {
		// Call to JavaScript "myfunction.js"
		injectMyFunctionScript();

		callApple();
	}

	private void injectMyFunctionScript() {
		ScriptInjector.fromUrl(MYFUNCTION_URL)
				.setCallback(new Callback<Void, Exception>() {
					@Override
					public void onFailure(Exception reason) {
						logger.info("Script load failed Info: " + reason);
					}

					@Override
					public void onSuccess(Void result) {
						logger.info(
								"MyFunction.js loaded successfully and executed!");
					}

				}).setRemoveTag(true).setWindow(ScriptInjector.TOP_WINDOW)
				.inject();
	}

	private void callApple() {
		logger.info("Calling Apple.");
		if (appleCount == 0) {
			logger.info("Apple Sum the first: " + apple.sum());
			appleCount++;
		} else {
			apple.x = 10;
			apple.y = 10;
			logger.info("Apple Sum the second and so on: " + apple.sum());
		}
	}

}
