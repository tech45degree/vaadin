package com.tech45degree.view;


import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

public class MainLayout  extends Div implements RouterLayout {

    MainLayout(){
        add(new H6("Main View"));
    }
}
