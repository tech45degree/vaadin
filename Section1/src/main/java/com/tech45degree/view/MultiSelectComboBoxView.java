package com.tech45degree.view;

import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBoxVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;


import java.util.stream.Collectors;

@Route("multiselectcombobox")
public class MultiSelectComboBoxView extends VerticalLayout {

    MultiSelectComboBoxView(){

        MultiSelectComboBox<String> cboNames = new MultiSelectComboBox<>("Names");
        cboNames.setItems(DataGenerator.getNames());

        cboNames.setClearButtonVisible(true);

        cboNames.addValueChangeListener(event -> {
           String names =  event.getValue().stream().collect(Collectors.joining(","));
           Notification.show(names);
        });

        MultiSelectComboBox<Employee> cboEmployees = new MultiSelectComboBox<>("Employees");
        cboEmployees.setItems(DataGenerator.getEmployees());
        cboEmployees.setClearButtonVisible(true);
      //  cboEmployees.setWidth("600px");
        cboEmployees.setItemLabelGenerator(employee -> employee.name()+" - "+employee.profession());

        cboEmployees.setAutoExpand(MultiSelectComboBox.AutoExpandMode.HORIZONTAL);
        cboEmployees.setSelectedItemsOnTop(true);

        cboEmployees.addValueChangeListener(event -> {
            String employeNames = event.getValue().stream().map(Employee::name).collect(Collectors.joining(","));
            Notification.show(employeNames);
        });

        cboEmployees.setRenderer(createEmployeeRenderer());

        add(cboNames,cboEmployees);
    }



    private Renderer<Employee> createEmployeeRenderer() {
        StringBuilder tpl = new StringBuilder();
        tpl.append("<div style=\"display: flex;\">");
        tpl.append("  <div>");
        tpl.append("    ${item.id} ${item.name}");
        tpl.append("    <div style=\"font-size: var(--lumo-font-size-s); color: red;\">${item.address}</div>");
        tpl.append("  </div>");
        tpl.append("</div>");

        return LitRenderer.<Employee> of(tpl.toString())
                .withProperty("id", Employee::id)
                .withProperty("name", Employee::name)
                .withProperty("address", Employee::address);
    }
}
