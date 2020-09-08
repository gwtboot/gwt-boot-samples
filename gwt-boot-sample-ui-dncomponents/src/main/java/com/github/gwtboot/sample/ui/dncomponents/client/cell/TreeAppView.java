package com.github.gwtboot.sample.ui.dncomponents.client.cell;

import com.dncomponents.UiField;
import com.dncomponents.client.components.Tree;
import com.dncomponents.client.components.core.HtmlBinder;
import com.dncomponents.client.components.textarea.TextArea;
import com.dncomponents.client.views.IsElement;
import com.github.gwtboot.sample.ui.dncomponents.client.helper.Fruit;
import elemental2.dom.HTMLElement;

public class TreeAppView implements IsElement {
    @UiField
    HTMLElement root;
    @UiField
    Tree<Object> tree;
    @UiField
    TextArea logTa;

    public TreeAppView() {
        HtmlBinder.get(TreeAppView.class, this).bind();
        init();
    }

    private void init() {
        tree.setRoot(Fruit.getFruitsTree());
        tree.drawData();
        tree.getSelectionModel().addSelectionHandler(evt ->
                evt.getSelectedItem().forEach(p ->
                        logTa.append(p.getUserObject() + "\n")));
    }

    @Override
    public HTMLElement asElement() {
        return root;
    }

    private static TreeAppView instance;

    public static TreeAppView getInstance() {
        if (instance == null)
            instance = new TreeAppView();
        return instance;
    }

}