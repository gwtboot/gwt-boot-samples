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
package com.github.gwtboot.sample.ui.dncomponents.client.greeting;

import com.dncomponents.client.views.appview.AbstractActivity;
import com.dncomponents.client.views.appview.Place;

public class GreetingPlace extends Place {

	public static final class GreetingPlaceRegister extends PlaceRegister<GreetingPlace> {

		public static GreetingPlaceRegister instance = new GreetingPlaceRegister();

		private GreetingPlaceRegister() {
		}

		private static final String TOKEN = "greeting";

		@Override
		public String getHistoryToken() {
			return TOKEN;
		}

		@Override
		public GreetingPlace getPlaceFromToken(String token) {
			return new GreetingPlace();
		}

		@Override
		public String getTokenFromPlace(GreetingPlace place) {
			return TOKEN;
		}

		@Override
		public AbstractActivity getActivity(GreetingPlace place) {
			return new GreetingActivity(GreetingViewImpl.getInstance(), place);
		}

		@Override
		public Class<? extends Place> forPlace() {
			return GreetingPlace.class;
		}

	}

}