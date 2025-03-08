package com.tech45degree.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.DenyAll;

@Route("restricted")
@DenyAll
public class RestrictedView extends VerticalLayout {
    public RestrictedView() {
        add(new H1("Access to this page is denied for everyone."));
    }
}