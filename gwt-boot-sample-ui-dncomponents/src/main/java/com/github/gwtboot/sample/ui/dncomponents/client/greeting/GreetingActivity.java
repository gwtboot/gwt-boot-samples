package com.github.gwtboot.sample.ui.dncomponents.client.greeting;

import com.dncomponents.client.views.appview.AbstractActivity;

public class GreetingActivity extends AbstractActivity<GreetingView, GreetingPlace> implements GreetingView.GreetingPresenter {

    public GreetingActivity(GreetingView view, GreetingPlace place) {
        super(view, place);
    }

    @Override
    public void onNameEntered(String name) {
        if (name == null || name.isEmpty() || name.length() < 4)
            view.setError("Name must be at least 4 characters long!");
        else {
            view.setError("");
            view.setName("Hello " + name + "!");
        }
    }
}
