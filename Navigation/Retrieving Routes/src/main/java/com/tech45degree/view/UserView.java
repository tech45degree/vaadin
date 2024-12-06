package com.tech45degree.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;

@Route(value = "user/:id([0-9]*)")
@PageTitle("User")
public class UserView extends VerticalLayout {

    UserView(){

        add(new H6("User View"));
    }

}
