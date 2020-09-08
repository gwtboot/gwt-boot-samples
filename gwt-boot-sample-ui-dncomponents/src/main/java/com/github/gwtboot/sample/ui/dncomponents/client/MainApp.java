package com.github.gwtboot.sample.ui.dncomponents.client;

import com.dncomponents.UiField;
import com.dncomponents.client.components.HasCellComponents;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.entities.ItemId;
import com.dncomponents.client.components.sidemenu.SideMenu;
import com.dncomponents.client.dom.History;
import com.dncomponents.client.views.IsElement;
import com.dncomponents.client.views.appview.AcceptsOneElement;
import com.dncomponents.client.views.appview.PlaceManager;
import com.github.gwtboot.sample.ui.dncomponents.client.cell.CellPlace;
import com.github.gwtboot.sample.ui.dncomponents.client.greeting.GreetingPlace;
import com.github.gwtboot.sample.ui.dncomponents.client.home.HomePlace;
import elemental2.dom.HTMLElement;
import elemental2.dom.Node;

public class MainApp implements AcceptsOneElement {

    @UiField
    HTMLElement contentWrapper;
    @UiField
    public SideMenu<ItemId> side;

    PlaceManager placeManager = new PlaceManager(this);
    HtmlBinder binder = HtmlBinder.get(MainApp.class, this);

    public MainApp() {
        binder.bind();
        init();
        side.setPlaceManager(placeManager);
        side.expandAll(false);
    }

    public void init() {
        placeManager.register(HomePlace.HomePlaceRegister.instance);
        placeManager.register(GreetingPlace.GreetingPlaceRegister.instance);
        placeManager.register(CellPlace.CellPlaceRegister.instance);
        placeManager.setHomePlace(HomePlace.class);
        History.fireCurrentHistoryState();
    }

    @Override
    public void setElement(IsElement element) {
        contentWrapper.innerHTML = "";
        contentWrapper.appendChild(element.asElement());
        if (element instanceof HasCellComponents) {
            ((HasCellComponents) element).resetScrollPosition();
        }
    }

    public Node asElement() {
        return binder.getTemplate().getCloned();
    }
}