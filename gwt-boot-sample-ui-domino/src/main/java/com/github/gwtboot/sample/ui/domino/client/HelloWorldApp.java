package com.github.gwtboot.sample.ui.domino.client;

import com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldView;
import org.dominokit.domino.ui.layout.Layout;
import org.dominokit.domino.ui.style.ColorScheme;

import static com.github.gwtboot.sample.ui.domino.client.ui.HelloWorldClientBundle.CONSTANTS;

public class HelloWorldApp {

    public void run() {
        Layout layout = Layout.create(CONSTANTS.appTitle())
                .removeLeftPanel()
                .show(ColorScheme.BLUE);

        HelloWorldView helloWorldView=new HelloWorldView();

        layout.getContentPanel()
                .appendChild(helloWorldView.asElement());
    }
}
