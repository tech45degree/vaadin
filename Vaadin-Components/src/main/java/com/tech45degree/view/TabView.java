package com.tech45degree.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.*;
import com.vaadin.flow.router.Route;

@Route("tabview")
public class TabView extends VerticalLayout {

    TabView(){

        VerticalLayout content = new VerticalLayout();

        Tab tabHome =  new Tab(VaadinIcon.HOME.create(),new Span("Home"));
        Tab tabPortfolio =  new Tab(new Span("Portfolio"),new Span("34"));
        Tab tabContactUs =  new Tab("Contact Us");

       // tabPortfolio.setEnabled(false);

      /*  for (Tab tab : new Tab[] { tabHome, tabPortfolio, tabContactUs }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }*/


        Tabs tabs = new Tabs(tabHome,tabPortfolio,tabContactUs);
       // tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setWidthFull();
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED,TabsVariant.LUMO_EQUAL_WIDTH_TABS);

        tabs.addSelectedChangeListener(event -> {

            content.removeAll();

            if(event.getSelectedTab() == tabHome){
                content.add(new Span("Home"));
            }else if(event.getSelectedTab() == tabPortfolio){
                content.add(new Span("Portfolio"));
            }else if(event.getSelectedTab() == tabContactUs){
                content.add(new Span("Contact Us"));
            }

        });

        tabs.setSelectedTab(tabContactUs);

        TabSheet tabSheet = new TabSheet();
        tabSheet.add("Home Tab",new Div("This is home tab"));
        Tab tabPortfolio2 = tabSheet.add("Portfolio Tab",new Div("This is portfolio tab"));
        tabSheet.add("Contact Us Tab",new Div("This is contact us tab"));

        tabPortfolio2.setEnabled(false);

        tabSheet.addClassName("my-tabsheet");

        add(tabs,content,tabSheet);
    }
}
