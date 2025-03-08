package com.tech45degree.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route("user")
@PageTitle("User")
@RolesAllowed("USER")
public class UserView extends VerticalLayout {

    public UserView() {
        add(new H1("User view"));
    }
}
