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
package com.github.gwtboot.sample.ui.dncomponents.client.helper;

import java.util.ArrayList;
import java.util.List;

public class Data {
	public static ArrayList<Person> people = TestingHelper.getPeople(1000);

	public static void setNulls(List<Person> people) {
		people.get(3).setName(null);
		people.get(3).setActive(null);
		people.get(3).setCurrentColor(null);
		people.get(3).setAge(null);
		//
		people.get(4).setName(null);
		people.get(4).setActive(null);
		people.get(4).setCurrentColor(null);
		people.get(4).setAge(null);
	}

	public static ArrayList<Person> getPeople() {
		ArrayList<Person> people = new ArrayList<>();
		int b = 0;
		for (int i = 0; i < 50; i++) {
			for (Person person : TestingHelper.getPeople(1000)) {
				Person t = new Person();
				t.setId(b++);
				t.setName(person.getName());
				t.setActive(person.isActive());
				t.setCurrentColor(person.getCurrentColor());
				t.setAge(person.getAge());
				t.setDate(person.getDate());
				people.add(t);
			}
		}
		return people;
	}

}
