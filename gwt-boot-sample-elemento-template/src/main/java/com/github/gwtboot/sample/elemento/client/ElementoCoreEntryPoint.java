package com.github.gwtboot.sample.elemento.client;

import com.google.gwt.core.client.EntryPoint;

import static org.jboss.gwt.elemento.core.Elements.body;

public class ElementoCoreEntryPoint implements EntryPoint {

    public void onModuleLoad() {
        body().add(QuoteGenerator.create());
    }
}
