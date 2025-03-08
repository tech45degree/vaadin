package com.tech45degree.view;

import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("listbox")
public class ListBoxView extends VerticalLayout {

    ListBoxView(){

        ListBox<String> lstbxname = new ListBox<>();
        lstbxname.setItems(DataGenerator.getNames());
        lstbxname.setValue("Jhon");
        lstbxname.setItemEnabledProvider(item -> !item.equalsIgnoreCase("Mike"));
        lstbxname.addComponents("Jenna",new Hr());

       /* lstbxname.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });*/
        lstbxname.addClassName("listbox-name");

        MultiSelectListBox<Employee> lstbxEmployee = new MultiSelectListBox<>();
        lstbxEmployee.setItems(DataGenerator.getEmployees());
       // lstbxEmployee.setItemLabelGenerator(item -> item.name()+" - "+item.profession());
        lstbxEmployee.setRenderer(createEmployeeRenderer());
        lstbxEmployee.setItemEnabledProvider(item -> !item.name().equalsIgnoreCase("Mike"));


        lstbxEmployee.addComponents(DataGenerator.getEmployees().get(0),new Hr());
        lstbxEmployee.select(DataGenerator.getEmployees().get(0),DataGenerator.getEmployees().get(2));
      /*  lstbxEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().toString());
        });*/

        add(lstbxname,lstbxEmployee);

    }


    private ComponentRenderer<HorizontalLayout, Employee> createEmployeeRenderer() {
        return new ComponentRenderer<>(employee -> {
            HorizontalLayout wrapper = new HorizontalLayout();
            wrapper.setAlignItems(Alignment.CENTER);


            Image image = new Image();
            image.setSrc("images/"+employee.id()+".png");
            image.setAlt("Portrait of " + employee.name());

            Div info = new Div();
            info.setText(employee.name());

            Div profession = new Div();
            profession.setText(employee.profession());
            profession.getStyle()
                    .set("color", "var(--lumo-secondary-text-color)")
                    .set("font-size", "var(--lumo-font-size-s)");
            info.add(profession);

            wrapper.add(image, info);
            return wrapper;
        });
    }
}
