package com.tech45degree.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;


@Route("contactUs")
public class ContactUsView extends VerticalLayout implements BeforeEnterObserver{

    public ContactUsView() {
        add(new H3("Contact Us"));

        RouterLink routerLinkAboutUs = new RouterLink("About US", AboutUsView.class);

        add(routerLinkAboutUs);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        boolean isAuthenticated = getAuthentication();

        if(!isAuthenticated) {
            beforeEnterEvent.rerouteTo(HomeView.class);
        }

    }

    private boolean getAuthentication() {
        return false;
    }

}
