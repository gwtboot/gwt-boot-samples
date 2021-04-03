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

import org.dominokit.jackson.annotation.JSONMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

@JSONMapper
public class PersonDto {

	private static Logger logger = Logger.getLogger(PersonDto.class.getName());

	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date date;

	private PersonType personType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PersonType getPersonType() {
		logger.info("getPersonType: " + personType);
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

}
