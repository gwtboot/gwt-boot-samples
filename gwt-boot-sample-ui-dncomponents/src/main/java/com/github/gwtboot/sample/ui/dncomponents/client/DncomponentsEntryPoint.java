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
package com.github.gwtboot.sample.ui.dncomponents.client;

import com.dncomponents.bootstrap.client.BootstrapUi;
import com.dncomponents.client.components.core.AppTemplates;
import com.dncomponents.client.views.Ui;
import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;

public class DncomponentsEntryPoint implements EntryPoint {


    @Override
    public void onModuleLoad() {
        //to work with java/html pairs use intellij dncomponents plugin https://plugins.jetbrains.com/plugin/13486-dn-components
        Ui.set(new BootstrapUi());
        //first you must run mvn compile to generate AppTemplates class then run app with mvn gwt:devmode
        AppTemplates.register();
        MainApp mainApp = new MainApp();
        DomGlobal.document.body.appendChild(mainApp.asElement());
    }
}