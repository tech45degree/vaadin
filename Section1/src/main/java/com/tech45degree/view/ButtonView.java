package com.tech45degree.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Svg;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("button")
public class ButtonView extends VerticalLayout {


    ButtonView(){

        Button btnShowMessage = new Button();
        btnShowMessage.setText("Show");
        btnShowMessage.setTooltipText("When it clicked it show message");
        btnShowMessage.setDisableOnClick(true);
        btnShowMessage.addClickShortcut(Key.ENTER);

     //   btnShowMessage.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);


      //  btnShowMessage.getStyle().set("background","blue");
     //   btnShowMessage.getStyle().set("color","white");

        btnShowMessage.addClassName("primary");

        StreamResource youtubeResource = new StreamResource("youtube.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/youtube.svg"));

        SvgIcon iconYoutube  = new SvgIcon(youtubeResource);
        iconYoutube.setColor("red");

        btnShowMessage.setIcon(iconYoutube);
        btnShowMessage.setIconAfterText(true);


        Button btnSecondary = new Button("Secondary");
        btnSecondary.setDisableOnClick(true);

        btnShowMessage.addClickListener(buttonClickEvent -> {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Notification.show("Hello World");
            btnShowMessage.setEnabled(true);
        });


        /*btnShowMessage.addFocusListener(buttonFocusEvent -> {
            Notification.show("Focused");
        });
        */

       /* btnShowMessage.addDoubleClickListener(buttonClickEvent -> {

            Notification.show("Double CLicked");

        });*/
        add(btnShowMessage,btnSecondary);

    }

}
