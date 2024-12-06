package com.tech45degree.view;

import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.rmi.NotBoundException;


public class ServicesView extends VerticalLayout implements BeforeEnterObserver {

    ServicesView(){
        add(new H6("Services View"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteConfiguration configuration = RouteConfiguration.forSessionScope();

       // configuration.removeRoute(ContactUsView.class);
        configuration.removeRoute("contactus");

        NotificationUtil.showErrorNotification("contactus has been removed!");

    }
}
