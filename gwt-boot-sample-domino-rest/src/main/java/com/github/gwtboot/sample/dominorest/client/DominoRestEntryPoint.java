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
package com.github.gwtboot.sample.dominorest.client;

import java.util.Date;
import java.util.logging.Logger;

import org.dominokit.domino.rest.DominoRestConfig;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;

public class DominoRestEntryPoint implements EntryPoint {

	private static Logger logger = Logger.getLogger(DominoRestEntryPoint.class.getName());

    @Override
	public void onModuleLoad() {
    	DominoRestConfig.initDefaults();

		DominoRestConfig.getInstance().setDefaultServiceRoot("http://localhost:9090/server");
		
		PersonDto coolPerson = new PersonDto();
		coolPerson.setDate(new Date());
		coolPerson.setName("Lofi");
		coolPerson.setPersonType(PersonType.COOL);

		Button personListButton = new Button("Click me: " + coolPerson.getPersonType().name());

		personListButton.addClickHandler(clickEvent -> {
			logger.info("Hello World: executePersonList");

			PersonClientFactory.INSTANCE.getPersons().onSuccess(response -> {
				response.forEach(p -> logger
						.info("Person: " + p.getName() + " - Date: " + p.getDate() + " - Type: " + p.getPersonType()));
			}).onFailed(failedResponse -> {
				logger.info(
						"Error: " + failedResponse.getStatusCode() + "\nMessages: " + failedResponse.getStatusText());
			}).send();
		});

    }

}
