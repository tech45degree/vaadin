package com.tech45degree.view;

import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "contactus")
@PageTitle("Contact Us")
public class ContactUsView extends VerticalLayout {

    ContactUsView(){

        add(new H6("Contact Us View"));
    }

}
