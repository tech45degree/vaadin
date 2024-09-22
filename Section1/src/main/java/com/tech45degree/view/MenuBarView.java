package com.tech45degree.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;



@Route("menubar")
public class MenuBarView extends VerticalLayout {

    MenuBarView(){


        MenuBar menuBar = new MenuBar();
        menuBar.addClassName("my-menu");
        menuBar.setOverlayClassName("my-menu-overlay");

        ComponentEventListener<ClickEvent<MenuItem>> listener = menuItemClickEvent ->
                Notification.show(menuItemClickEvent.getSource().getText());

        MenuItem itemHome =  menuBar.addItem("Home",listener);

        MenuItem itemPortfolio = menuBar.addItem("Portfolio",listener);
        MenuItem itemAboutUs = menuBar.addItem("About Us",listener);
        MenuItem itemShare = menuBar.addItem("Share",listener);
        itemShare.setEnabled(false);

        SubMenu subMenuAboutUs = itemAboutUs.getSubMenu();
        MenuItem itemInstagram = subMenuAboutUs.addItem("Instagram",listener);

        Icon icoFacebook = VaadinIcon.FACEBOOK.create();
        icoFacebook.setColor("blue");

        Span spanTxtFB = new Span("Facebook");

        Div divFacebook = new Div(icoFacebook, spanTxtFB);

        MenuItem itemFacebook = subMenuAboutUs.addItem(divFacebook,listener);
        itemFacebook.setEnabled(false);


        MenuItem itemWhatsapp = subMenuAboutUs.addItem("Whatsapp",listener);

        itemInstagram.setCheckable(true);
        itemFacebook.setCheckable(true);
        itemFacebook.setChecked(true);


        /*itemHome.addClickListener(menuItemClickEvent -> {
            Notification.show("Home item clicked");
        });*/

        add(menuBar);

    }
}
