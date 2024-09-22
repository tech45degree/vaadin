package com.tech45degree.view;

import com.vaadin.flow.component.icon.FontIcon;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoIcon;

@Route("icon")
public class IconView extends VerticalLayout {

    IconView() {
        Icon icoTaxi = VaadinIcon.TAXI.create();
        icoTaxi.setSize("64px");
        icoTaxi.setColor("red");


        Icon icoAngleDown = LumoIcon.ANGLE_DOWN.create();
        icoAngleDown.addClassName("redText");

        StreamResource resourceFacebook = new StreamResource("facebook.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/facebook.svg"));

        SvgIcon iconFacebook = new SvgIcon(resourceFacebook);
        iconFacebook.setColor("blue");

        StreamResource resourceYoutube = new StreamResource("youtube.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/youtube.svg"));

        SvgIcon iconYoutube = new SvgIcon(resourceYoutube);
        iconYoutube.setColor("red");

        StreamResource resourceCustomIcons = new StreamResource("customIcons.svg",
                () -> getClass().getResourceAsStream("/META-INF/resources/icons/customIcons.svg"));


        SvgIcon icoYoutube2 = new SvgIcon(resourceCustomIcons,"youtube");

        SvgIcon icoFacebook2 = new SvgIcon(resourceCustomIcons,"facebook");

        FontIcon fontIconGlass = new FontIcon("fa","fa-glass");
        fontIconGlass.setColor("yellow");

        FontIcon fontIconUser = new FontIcon("fa","fa-user");
        fontIconUser.addClassName("redText");


        add(icoTaxi,icoAngleDown,iconFacebook,iconYoutube,icoYoutube2,icoFacebook2,fontIconGlass,fontIconUser);

    }
}
