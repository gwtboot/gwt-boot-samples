package com.github.gwtboot.sample.ui.dncomponents.client.greeting;

import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.appview.HasPresenter;
import com.dncomponents.client.views.appview.Presenter;

public interface GreetingView extends IsElement, HasPresenter<GreetingActivity> {

    interface GreetingPresenter extends Presenter {
        void onNameEntered(String name);
    }

    void setName(String name);

    void setError(String error);
}