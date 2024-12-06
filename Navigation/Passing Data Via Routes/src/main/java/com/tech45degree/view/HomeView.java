package com.tech45degree.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import java.util.*;


@Route
public class HomeView extends VerticalLayout {

    public HomeView(){
        add(new H3("Home"));

        Map<String, List<String>> parametersMap = new HashMap<>();
        parametersMap.put("name", Arrays.asList("Jhon"));
        parametersMap.put("phone", Arrays.asList("12234343"));
        parametersMap.put("email", Arrays.asList("jhon@tech45degree.com"));

        QueryParameters queryParameters = new QueryParameters(parametersMap);

        Button btnUser = new Button("User");
        btnUser.addClickListener(e -> {
           // UI.getCurrent().navigate(UserView.class,"123");
            UI.getCurrent().navigate("user/123/edit/455445");
           // UI.getCurrent().navigate(UserView.class, queryParameters);
        });

        Button btnProduct = new Button("Product");
        btnProduct.addClickListener(e -> {
            UI.getCurrent().navigate(ProductView.class,"166563/334/434");
        });

        add(btnUser,btnProduct);


    }

}
