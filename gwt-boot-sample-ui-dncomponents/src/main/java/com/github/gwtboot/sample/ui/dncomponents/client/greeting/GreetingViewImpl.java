package com.github.gwtboot.sample.ui.dncomponents.client.greeting;

import com.dncomponents.UiField;
import com.dncomponents.client.components.button.Button;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.popover.Popover;
import com.dncomponents.client.components.textbox.TextBox;
import com.dncomponents.client.dom.handlers.OnBlurHandler;
import com.dncomponents.client.views.appview.AbstractView;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLHeadingElement;

public class GreetingViewImpl extends AbstractView<GreetingActivity> implements GreetingView {
    private static GreetingViewImpl instance;

    @UiField
    HTMLElement root;
    @UiField
    public HTMLHeadingElement nameLabel;
    @UiField
    public Button button;
    @UiField
    public TextBox nameField;
    @UiField
    public HTMLElement errorLabel;
    @UiField
    public Popover popover;


    HtmlBinder binder = HtmlBinder.get(GreetingViewImpl.class, this);

    private GreetingViewImpl() {
        binder.bind();
        init();
    }

    private void init() {
        button.addClickHandler(e -> onNameEntered());
        nameField.addValueChangeHandler(e -> onNameEntered());
        nameField.addHandler((OnBlurHandler) focusEvent -> popover.hide());
    }

    private void onNameEntered() {
        presenter.onNameEntered(nameField.getValue());
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    @Override
    public void setName(String name) {
        nameLabel.textContent = name;
    }

    @Override
    public void setError(String error) {
        if (!error.isEmpty()) {
            popover.setTitle("Error");
            popover.setContent(error);
            nameField.setFocus(true);
            popover.show();
        }
        errorLabel.textContent = error;
    }

    public static GreetingViewImpl getInstance() {
        if (instance == null) instance = new GreetingViewImpl();
        return instance;
    }

}
