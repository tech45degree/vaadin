package com.tech45degree.view;

import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route(value = "")
public class MenuView extends VerticalLayout{

    MenuView(){

        add(new H6("Menu View"));

        RouteConfiguration configuration = RouteConfiguration.forSessionScope();



        configuration.setRoute("user",UserView.class);
        configuration.setRoute("service",ServicesView.class);
        configuration.setRoute("contactus",ContactUsView.class);

    }
}
