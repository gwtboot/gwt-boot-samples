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
package com.github.gwtboot.sample.elemento.client;

import static com.github.gwtboot.sample.elemento.client.Css.backgroundSecondary;
import static com.github.gwtboot.sample.elemento.client.Css.backgroundSuccess;
import static com.github.gwtboot.sample.elemento.client.Css.border;
import static com.github.gwtboot.sample.elemento.client.Css.card;
import static com.github.gwtboot.sample.elemento.client.Css.cardBody;
import static com.github.gwtboot.sample.elemento.client.Css.center;
import static com.github.gwtboot.sample.elemento.client.Css.container;
import static com.github.gwtboot.sample.elemento.client.Css.flexCenter;
import static com.github.gwtboot.sample.elemento.client.Css.flexTop;
import static com.github.gwtboot.sample.elemento.client.Css.marginRightLarge;
import static com.github.gwtboot.sample.elemento.client.Css.marginTopLarge;
import static com.github.gwtboot.sample.elemento.client.Css.paddingLeftLarge;
import static com.github.gwtboot.sample.elemento.client.Css.paddingRightLarge;
import static com.github.gwtboot.sample.elemento.client.Css.row;
import static com.github.gwtboot.sample.elemento.client.Css.shadow;
import static elemental2.core.Global.encodeURIComponent;
import static elemental2.dom.DomGlobal.window;
import static org.jboss.elemento.Elements.body;
import static org.jboss.elemento.Elements.button;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.h;
import static org.jboss.elemento.Elements.header;
import static org.jboss.elemento.Elements.main;
import static org.jboss.elemento.Elements.p;
import static org.jboss.elemento.EventType.click;

import java.util.Random;

import com.google.gwt.core.client.EntryPoint;

import elemental2.dom.HTMLElement;

public class ElementoTemplateEntryPoint implements EntryPoint {

    private final String[] QUOTES = {
            "They may take our lives, but they'll never take our freedom!",
            "I'm sorry, Dave. I'm afraid I can't do that.",
            "When you realize you want to spend the rest of your life with somebody, you want the rest of your life to start as soon as possible.",
            "They call it a Royale with cheese.",
            "The Dude abides.",
            "It's 106 miles to Chicago, we've got a full tank of gas, half a pack of cigarettes, it's dark and we're wearing sunglasses. Hit it!",
            "Make it so",
            "Hasta la vista, baby.",
            "I'm having an old friend for dinner.",
            "I'm not bad. I'm just drawn that way.",
            "The greatest trick the devil ever pulled was convincing the world he didn't exist.",
            "Carpe diem. Seize the day, boys.",
            "Yippie-ki-yay, motherf***er!",
            "You can't handle the truth!",
            "I'll be back.",
            "I see dead people.",
            "I am your father.",
            "The first rule of Fight Club is: You do not talk about Fight Club.",
            "Soylent Green is people!",
    };

    private HTMLElement quote;

    @Override
	public void onModuleLoad() {
        body().add(div().css(container)
                .add(div().css(row, flexTop, flexCenter, marginTopLarge)
                        .add(header().css(shadow, border)
                                .add(h(2, "Random Quote Generator")
                                        .css(paddingLeftLarge, paddingRightLarge))))
                .add(div().css(row, flexCenter, marginTopLarge)
                        .add(button()
                                .css(backgroundSuccess, marginRightLarge)
                                .on(click, evt -> newQuote())
                                .textContent("New Quote"))
                        .add(button()
                                .css(backgroundSecondary)
                                .on(click, evt -> tweetQuote())
                                .textContent("Tweet Quote")))
                .add(div().css(row, flexCenter, marginTopLarge)
                        .add(main().css(card).style("width: 33rem")
								.add(quote = p().css(cardBody, center).element()))));
    }

    private void newQuote() {
        int index = new Random().nextInt(QUOTES.length);
        quote.textContent = QUOTES[index];
    }

    private void tweetQuote() {
        String generatedQuote = quote.textContent;
        if (generatedQuote != null && generatedQuote.length() != 0) {
            String url = "https://twitter.com/intent/tweet?text=" + encodeURIComponent(generatedQuote);
            window.open(url);
        }
    }
}
