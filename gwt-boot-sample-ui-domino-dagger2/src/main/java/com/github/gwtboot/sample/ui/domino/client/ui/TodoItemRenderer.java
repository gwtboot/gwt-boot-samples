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
package com.github.gwtboot.sample.ui.domino.client.ui;

import java.util.function.Consumer;

import org.dominokit.domino.ui.grid.flex.FlexItem;
import org.dominokit.domino.ui.grid.flex.FlexJustifyContent;
import org.dominokit.domino.ui.grid.flex.FlexLayout;
import org.dominokit.domino.ui.header.BlockHeader;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.Styles;

public class TodoItemRenderer implements ListGroup.ItemRenderer<TodoItem> {

    private Consumer<TodoItem> onCheckHandler = todoItem -> {};

    @Override
    public void onRender(ListGroup<TodoItem> listGroup, ListItem<TodoItem> listItem) {
        listItem.css(Styles.padding_10)
                .appendChild(FlexLayout.create().setJustifyContent(
                        FlexJustifyContent.SPACE_AROUND)
                        .appendChild(FlexItem.create().setFlexGrow(1)
                                .appendChild(BlockHeader.create(
                                        listItem.getValue().getTitle(),
                                        listItem.getValue().getDescription())
                                        .css(Styles.m_b_0)))
                        .appendChild(FlexItem.create()
                                .appendChild(Icons.ALL.check_bold_mdi()
                                        .setColor(Color.GREEN)
                                        .clickable()
                                        .addClickListener(
                                                addClickEvent -> onCheckHandler.accept(listItem.getValue()))
                                ))
                );
    }

    public void setOnCheckHandler(Consumer<TodoItem> onCheckHandler) {
        this.onCheckHandler = onCheckHandler;
    }
    
}
