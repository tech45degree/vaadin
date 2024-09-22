package com.tech45degree.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("badge")
public class BadgeView extends VerticalLayout {

    BadgeView(){

        Icon iconPending = VaadinIcon.CLOCK.create();
        iconPending.getStyle().set("padding","6px");

        Span badgePending =  new Span(iconPending,new Span("Pending"));
        badgePending.getElement().getThemeList().add("badge contrast primary");

        Div badgeError = new Div("Error");
        badgeError.getElement().getThemeList().add("badge error primary");

        Span badgeCompleted = new Span("completed!");
        badgeCompleted.getElement().getThemeList().add("badge success");

        Span badgeNotifications = new Span("5 pending notifications");
        badgeNotifications.getElement().getThemeList().add("badge error");

        Span spanNotification = new Span( new Span("Your order has been successfully"),badgeCompleted, new Span("Thank you for your purchase. Please note that you have "),badgeNotifications, new Span(" in your account. These might include updates, promotional offers, or important information related to your recent activity. Be sure to check them out to stay informed and make the most of your experience."));


        add(badgePending,badgeError,spanNotification);


        setPadding(true);
        setMargin(true);

    }


}
