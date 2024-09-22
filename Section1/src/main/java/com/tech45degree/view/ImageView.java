package com.tech45degree.view;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("image")
public class ImageView extends VerticalLayout {

    ImageView(){

        Image carImage = new Image();
        carImage.setSrc("images/redcar.png");
        carImage.setAlt("Image is not available");
     //   carImage.addClassName("redCar");


        StreamResource resourceUserImage = new StreamResource("1.png",
                () -> getClass().getResourceAsStream("/META-INF/resources/images/1.png"));

        Image userImage = new Image(resourceUserImage,"The image is not available");
        userImage.getStyle().set("background","red");
        userImage.getStyle().set("padding","6px");

        add(carImage,userImage);

    }
}
