package com.tech45degree.view;

import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;


@Route("select")
public class SelectView extends VerticalLayout {

    SelectView(){

        Select<String> selectNames = new Select<>();
        selectNames.setLabel("Select Employee Name");
        selectNames.setItems(DataGenerator.getNames());

        selectNames.setPlaceholder("Employee Name");
        selectNames.setTooltipText("Select Valid Employee Name");
        selectNames.setErrorMessage("Employee Name should be valid");

        Icon icon = new Icon(VaadinIcon.USER);

        selectNames.setPrefixComponent(icon);

       // selectNames.setItemEnabledProvider(item -> null != item && !item.equalsIgnoreCase("Mike"));

        selectNames.setValue("Jhon");

        selectNames.setEmptySelectionAllowed(true);
        selectNames.setEmptySelectionCaption("Empty Item");

        selectNames.addComponents("Mike",new Hr());


        selectNames.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });

        selectNames.addClassName("emp-select");
        selectNames.setOverlayClassName("emp-select-overlay");

        add(selectNames);


        Select<Employee> selectEmployee = new Select<>();
        selectEmployee.setLabel("Select Employee");
        selectEmployee.setItems(DataGenerator.getEmployees());

        selectEmployee.setRenderer(createEmployeeRenderer());

        selectEmployee.setItemLabelGenerator(item -> item.name() + " - " +item.phone());



        add(selectEmployee);


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
