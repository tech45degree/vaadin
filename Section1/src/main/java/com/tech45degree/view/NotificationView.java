package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("notification")
public class NotificationView extends VerticalLayout {

    Notification notification;
    NotificationView(){

      //  Notification notification = new Notification("Hello Tech45Degree",5000, Notification.Position.BOTTOM_CENTER);
        notification = new Notification();
        notification.setText("Welcome to t45");
        notification.setDuration(20000);
        notification.setPosition(Notification.Position.TOP_STRETCH);

       // notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

       // notification.addClassName("primary");

       //notification.add(displayMessage());

        Button btnShowNotification = new Button("Show Notification");

        btnShowNotification.addClickListener(event -> {

          // Notification.show("Hello Tech45Degree",5000, Notification.Position.MIDDLE);
            notification.open();

        });

        Button btnClose = new Button("Close Notification");
        btnClose.addClickListener(buttonClickEvent -> notification.close());

        add(btnShowNotification,btnClose);
    }


    private VerticalLayout displayMessage(){
        VerticalLayout verticalLayout = new VerticalLayout();

        H6 message = new H6("Your Session has been expired");
        H6 message2 =  new H6("Please login again");

        Button btnCLose = new Button(VaadinIcon.CLOSE.create());
        btnCLose.addClickListener(event -> {
            notification.close();
        });

        verticalLayout.add(btnCLose,message,new Hr(),message2);

        return verticalLayout;
    }
}
