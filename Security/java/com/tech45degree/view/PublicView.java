package com.tech45degree.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("public")
@AnonymousAllowed
public class PublicView extends VerticalLayout {
    public PublicView() {
        add(new H1("This page is accessible to everyone."));
    }
}