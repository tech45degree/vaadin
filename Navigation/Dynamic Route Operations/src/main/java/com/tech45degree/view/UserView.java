package com.tech45degree.view;

import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

public class UserView extends VerticalLayout implements BeforeEnterObserver{

    UserView(){

        add(new H6("User View"));
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();

        configuration.getAvailableRoutes().forEach(route ->
                NotificationUtil.showSuccessNotification(route.getTemplate()));
    }
}
