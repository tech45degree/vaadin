package com.tech45degree.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MenuBarView.class)
@PageTitle("Home")
public class HomeView extends VerticalLayout {

    HomeView(){
        add(new H6("Home View"));
    }
}
