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
import com.axellience.vuegwt.core.annotations.component.Data;
import com.axellience.vuegwt.core.client.component.IsVueComponent;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;

/**
 * A simple Todo list. Is able to list some todo, mark them as done, remove done todos or all at the
 * same time.
 */
@Component(components = TodoComponent.class)
public class TodoListComponent implements IsVueComponent {

  @Data
  @JsProperty
  List<Todo> todos = new LinkedList<>();

  @Data
  String newTodoText = "";

  @JsMethod
  public void addTodo() {
    this.todos.add(new Todo(this.newTodoText));
    this.newTodoText = "";
  }

  @JsMethod
  public void removeTodo(Todo todo) {
    this.todos.remove(todo);
  }

  @JsMethod
  public void clearTodos() {
    this.todos.clear();
  }

  @JsMethod
  public void clearDoneTodos() {
    this.todos =
        this.todos.stream().filter(todo -> !todo.isDone()).collect(Collectors.toList());
  }

  /**
   * Will be automatically called by Vue.js to get the value of doneTodos in the template
   *
   * @return The number of todos that are done
   */
  @Computed
  public long getDoneTodos() {
    if (this.todos == null) {
      return 0;
    }

    return this.todos.stream().filter(Todo::isDone).count();
  }
}
