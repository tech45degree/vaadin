package com.tech45degree.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;



@Route("aboutUs")
public class AboutUsView extends VerticalLayout implements AfterNavigationObserver {


    TextField txtName = null;

    public AboutUsView() {
        add(new H3("About Us"));

        RouterLink routerLinkContactUs = new RouterLink("Contact Us", ContactUsView.class);


        txtName = new TextField("Name");

        add(routerLinkContactUs,txtName);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        txtName.setValue("Jhon");
    }
}
