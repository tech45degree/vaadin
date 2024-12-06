package com.tech45degree.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Layout
@ParentLayout(MainLayout.class)
public class MenuBarView extends VerticalLayout implements RouterLayout {

    MenuBarView(){

        MenuBar menuBar = new MenuBar();

        ComponentEventListener<ClickEvent<MenuItem>> listener = e ->
        {
            if(e.getSource().getText().equalsIgnoreCase("Home"))
            {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate(HomeView.class);
                });
            }else if(e.getSource().getText().equalsIgnoreCase("Contact Us"))
            {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate(ContactUsView.class);
                });
            }else if(e.getSource().getText().equalsIgnoreCase("Services"))
            {
                e.getSource().getUI().ifPresent(ui -> {
                    ui.navigate("service");
                });
            }
        };

        menuBar.addItem("Home", listener);
        menuBar.addItem("Contact Us", listener);
        menuBar.addItem("Services", listener);

        add(menuBar);

    }
}
