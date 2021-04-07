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

import com.dncomponents.UiField;
import com.dncomponents.client.components.ColumnConfig;
import com.dncomponents.client.components.Table;
import com.dncomponents.client.components.autocomplete.AutoCompleteEditor;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.table.header.HeaderTableFilterCell;
import com.dncomponents.client.components.table.header.filter.FilterPanelList;
import com.dncomponents.client.views.IsElement;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.Data;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.Person;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.TestingHelper;
import elemental2.dom.HTMLElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableAppView implements IsElement {

    @UiField
    HTMLElement root;
    @UiField
    Table<Person> table;

    public TableAppView() {
        HtmlBinder.get(TableAppView.class, this).bind();
        init();
    }

    private void init() {
        final ArrayList<Person> people = Data.getPeople();
        Data.setNulls(people);
        table.setMultiSorting(true);
        table.setEditable(true);

        ColumnConfig<Person, String> nameColumn =
                new ColumnConfig.Builder<>(Person::getName, Person::setName)
                        .setName("Name")
                        .setHeaderCellFactory(()
                                -> new HeaderTableFilterCell())
                        .setColumnWidth("300px")
                        .setEditable(true)
                        .setClazz(String.class)
                        .build();

        ColumnConfig<Person, Boolean> activeColumn =
                new ColumnConfig.Builder<>(Person::isActive, Person::setActive)
                        .setName("Active")
                        .setHeaderCellFactory(() -> new HeaderTableFilterCell())
                        .setColumnWidth("150px")
                        .setClazz(Boolean.class)
                        .build();


        ColumnConfig<Person, Integer> ageColumn =
                new ColumnConfig.Builder<>(Person::getAge, Person::setAge)
                        .setName("Age")
                        .setHeaderCellFactory(() -> new HeaderTableFilterCell())
                        .setColumnWidth("150px")
                        .setClazz(Integer.class)
                        .build();

        List<String> colors = Arrays.asList(TestingHelper.colors);

        final ColumnConfig<Person, String> colorColumn =
                new ColumnConfig.Builder<>(Person::getCurrentColor, Person::setCurrentColor)
                        .setName("Color")
                        .setHeaderCellFactory(() ->
                                new HeaderTableFilterCell()
                                        .setFilterPanel(new FilterPanelList(colors))
                        ).setColumnWidth("250px")
                        .build();

        AutoCompleteEditor<String> acEditor = new AutoCompleteEditor<>(TestingHelper.getColors());

        colorColumn.setCellFactory(c -> c.createDefaultCell()
                .setCellEditor(acEditor)
                .setRenderer(r -> {
                    r.valuePanel.style.background = "" + r.value;
                    r.valuePanel.innerHTML = r.value + "";
                }));

        table.addColumn(nameColumn, activeColumn, ageColumn, colorColumn);
        table.setRowsData(people);
        table.drawData();
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    private static TableAppView instance;

    public static TableAppView getInstance() {
        if (instance == null)
            instance = new TableAppView();
        return instance;
    }

}
