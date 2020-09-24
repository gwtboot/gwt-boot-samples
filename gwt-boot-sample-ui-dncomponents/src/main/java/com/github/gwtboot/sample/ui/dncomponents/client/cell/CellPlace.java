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
package com.github.gwtboot.sample.ui.dncomponents.client.cell;

import java.util.Objects;

import com.dncomponents.client.views.appview.AbstractActivity;
import com.dncomponents.client.views.appview.DefaultActivity;
import com.dncomponents.client.views.appview.Place;
import com.dncomponents.client.views.core.EnumLookUp;

public class CellPlace extends Place {
	{
		this.placeRegister = CellPlaceRegister.instance;
	}

	public enum Type {
		TABLE, TREE, LIST;

		public static EnumLookUp<Type> lookUp = new EnumLookUp<>(Type.values());
	}

	private Type type = Type.TABLE;

	public CellPlace() {
	}

	public CellPlace(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CellPlace that = (CellPlace) o;
		return type == that.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	public static final class CellPlaceRegister extends PlaceRegister<CellPlace> {

		public static CellPlaceRegister instance = new CellPlaceRegister();

		private CellPlaceRegister() {
		}

		private static final String TOKEN = "cell";

		@Override
		public String getHistoryToken() {
			return TOKEN;
		}

		@Override
		public CellPlace getPlaceFromToken(String token) {
			CellPlace tp = new CellPlace();
			String typeString = token.substring(token.indexOf(DIVIDER) + 1);
			Type type = Type.lookUp.getValue(typeString);
			if (type != null)
				tp.setType(type);
			return tp;
		}

		@Override
		public String getTokenFromPlace(CellPlace place) {
			return TOKEN + DIVIDER + place.type;
		}

		@Override
		public AbstractActivity<?, ?> getActivity(CellPlace place) {
			switch (place.getType()) {
			case TREE:
				return new DefaultActivity(TreeAppView.getInstance(), place);
			case LIST:
				return new DefaultActivity(ListAppView.getInstance(), place);
			default:
				return new DefaultActivity(TableAppView.getInstance(), place);
			}
		}

		@Override
		public Class<? extends Place> forPlace() {
			return CellPlace.class;
		}

	}
}