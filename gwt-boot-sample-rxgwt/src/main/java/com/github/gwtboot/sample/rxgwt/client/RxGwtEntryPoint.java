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
package com.github.gwtboot.sample.rxgwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.intendia.rxgwt2.elemento.RxElemento;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import io.reactivex.Observable;
import org.jboss.gwt.elemento.core.Elements;

import java.util.function.DoubleUnaryOperator;
import java.util.logging.Logger;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.jboss.gwt.elemento.core.InputType.number;

public class RxGwtEntryPoint implements EntryPoint {

    private static Logger logger = Logger
            .getLogger(RxGwtEntryPoint.class.getName());

    HTMLInputElement addInputElement;
    HTMLButtonElement addButtonElement;
    HTMLInputElement subInputElement;
    HTMLButtonElement subButtonElement;
    HTMLButtonElement resetButtonElement;
    HTMLDivElement resultDivElement;

    @Override
    public void onModuleLoad() {
        Elements.body().add(table()
                .add(tr()
                        .add(td().add(addInputElement = input(number).apply(el -> el.valueAsNumber = 1).get()))
                        .add(td().add(addButtonElement = button("add").get())))
                .add(tr()
                        .add(td().add(subInputElement = input(number).apply(el -> el.valueAsNumber = 1).get()))
                        .add(td().add(subButtonElement = button("sub").get())))
                .add(tr()
                        .add(td().add(resultDivElement = div().get()))
                        .add(td().add(resetButtonElement = button("reset").get()))));

        Observable<DoubleUnaryOperator> action$ = Observable.merge(
                RxElemento.fromEvent(addButtonElement, click).map(ev -> addInputElement.valueAsNumber).map(val -> n1 -> n1 + val),
                RxElemento.fromEvent(subButtonElement, click).map(ev -> subInputElement.valueAsNumber).map(val -> n1 -> n1 - val),
                RxElemento.fromEvent(resetButtonElement, click).map(ev -> n1 -> 0));

        action$.scan(0., (acc, n) -> n.applyAsDouble(acc))
                .doOnNext(n -> logger.info("value change: " + n))
                .subscribe(n -> resultDivElement.textContent = Double.toString(n));
    }
}
