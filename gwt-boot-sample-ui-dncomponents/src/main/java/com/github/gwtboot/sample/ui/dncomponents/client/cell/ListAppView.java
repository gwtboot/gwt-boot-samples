package com.github.gwtboot.sample.ui.dncomponents.client.cell;

import com.dncomponents.UiField;
import com.dncomponents.client.components.HasCellComponents;
import com.dncomponents.client.components.ListData;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.core.entities.ItemId;
import com.dncomponents.client.components.textarea.TextArea;
import com.dncomponents.client.views.IsElement;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.Data;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.Person;
import elemental2.dom.HTMLElement;

public class ListAppView implements IsElement, HasCellComponents {
    @UiField
    HTMLElement root;
    @UiField
    ListData<ItemId, String> list;
    @UiField
    ListData<Person, String> listPeople;
    @UiField
    TextArea logTa;
    @UiField
    TextArea logTa2;

    public ListAppView() {
        HtmlBinder.get(ListAppView.class, this).bind();
        init();
    }


    private void init() {
        list.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa.append(p.getId() + "\n")));

        listPeople.getRowCellConfig().setFieldGetter(Person::getName);
        listPeople.getRowCellConfig().getCellBuilder()
                .setCellRenderer(r -> r.valuePanel.innerHTML = "<b>" + r.value + "</b>");
        listPeople.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa2.append(p.getName() + " " + p.getAge() + "\n")
                ));

        listPeople.setRowsData(Data.people);
        listPeople.drawData();
    }


    @Override
    public HTMLElement asElement() {
        return root;
    }

    private static ListAppView instance;

    public static ListAppView getInstance() {
        if (instance == null)
            instance = new ListAppView();
        return instance;
    }

    @Override
    public void resetScrollPosition() {
        HasCellComponents.resetAll(listPeople, list);
    }

}