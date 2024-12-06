package com.tech45degree.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route(value = "")
public class MenuView extends VerticalLayout{

    MenuView(){

        String userRoute = RouteConfiguration.forSessionScope()
                .getUrl(UserView.class,new RouteParameters("id","567"));

        String ServiceRoute = RouteConfiguration.forSessionScope()
                .getUrl(ServicesView.class);

        Anchor linkUser = new Anchor(userRoute, "User");
        Anchor linkServices = new Anchor(ServiceRoute, "Services");

        add(linkUser,linkServices);

    }
}
