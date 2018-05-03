package com.github.gwtboot.sample.ui.vuegwt.client.components.counter;

import com.axellience.vuegwt.core.annotations.component.Component;
import com.axellience.vuegwt.core.client.component.VueComponent;
import jsinterop.annotations.JsProperty;

@Component
public class CounterComponent extends VueComponent
{
    @JsProperty int counterValue = 0;
}