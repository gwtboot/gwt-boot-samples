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
package com.github.gwtboot.sample.ui.vuegwt.client.components.todolist;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.annotations.component.Computed;
import com.axellience.vuegwt.core.annotations.component.Prop;
import com.axellience.vuegwt.core.client.component.VueComponent;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 * Display a Todo.
 */
@Component
public class TodoComponent extends VueComponent {

    @Prop
    @JsProperty
    Todo todo;

    /**
     * Emit an event when we want to delete the todo
     */
    @JsMethod
    public void removeTodo() {
        this.$emit("removeTodo", todo);
    }

    @Computed
    public boolean getIsDoneTodo() {
        return this.todo.isDone();
    }

    @Computed
    public void setIsDoneTodo(boolean isDone) {
        this.todo.setDone(isDone);
    }
}