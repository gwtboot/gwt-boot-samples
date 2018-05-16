package com.github.gwtboot.sample.elemento.client;

import java.util.Random;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.HTMLElement;

import static com.github.gwtboot.sample.elemento.client.CSS.*;
import static elemental2.core.Global.encodeURIComponent;
import static elemental2.dom.DomGlobal.window;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;

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
                                .add(quote = p().css(cardBody, center).asElement()))));
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
