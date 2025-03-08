package com.tech45degree.view;


import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("avatar")
public class AvatarView extends VerticalLayout {

    AvatarView(){

        Avatar avatar = new Avatar();
        avatar.setName("Jhon");
        avatar.setAbbreviation("JH");
        avatar.setColorIndex(4);
        avatar.setImage("images/3.png");

       // avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL);
        //avatar.addClassName("userImage");

        AvatarGroup group = new AvatarGroup();
       // group.addClassName("avtGrp");
        //group.setOverlayClassName("avtGrpOverlay");
        group.setMaxItemsVisible(3);

        for(Employee employee:DataGenerator.getEmployees()){
            AvatarGroup.AvatarGroupItem item = new AvatarGroup.AvatarGroupItem();
            item.setName(employee.name());
            item.setColorIndex((int) employee.id());
            item.setImage("images/" + employee.id() + ".png");
            group.add(item);
        }


        add(avatar,group);
    }
}
