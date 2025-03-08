package com.tech45degree.view;

import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.data.renderer.Renderer;


@Route("combobox")
public class ComboBoxView extends VerticalLayout {

    public ComboBoxView(){

        // Step 1
        ComboBox<String> cboNames = new ComboBox<>();
        cboNames.setLabel("Name");


        // Step 2
        cboNames.setItems(DataGenerator.getNames());

        cboNames.setValue("Kyle");

        cboNames.addValueChangeListener(event -> {
            Notification.show(event.getOldValue());
        });

        ComboBox<Boolean> cboYesNo =  new ComboBox<>();

        cboYesNo.setItems(DataGenerator.getBooleans());

        ComboBox<Integer> cboAge = new ComboBox<>("Age");
        cboAge.setClassName("cboage");
        cboAge.setOverlayClassName("cboage");

        cboAge.setItems(DataGenerator.getIntegers());


        ComboBox<Employee> cboEmployee = new ComboBox<>("Employee");

        cboEmployee.setItems(DataGenerator.getEmployees());

        cboEmployee.setItemLabelGenerator(employee -> employee.id()+ " - "+employee.name());

        cboEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().phone() +" - "+event.getValue().address());
        });

        cboEmployee.setRenderer(createEmployeeRenderer());

        
        // Step 3
        add(cboNames,cboYesNo,cboAge);
        add(cboEmployee);

        cboNames.addThemeVariants(ComboBoxVariant.LUMO_ALIGN_RIGHT,ComboBoxVariant.LUMO_SMALL);


    }

    private Renderer<Employee> createEmployeeRenderer() {
        StringBuilder tpl = new StringBuilder();
        tpl.append("<div style=\"display: flex;\">");
        tpl.append("  <div>");
        tpl.append("    ${item.keyid} ${item.keyname}");
        tpl.append("    <div style=\"font-size: var(--lumo-font-size-s); color: blue;\">${item.keyaddress}</div>");
        tpl.append("  </div>");
        tpl.append("</div>");

        return LitRenderer.<Employee> of(tpl.toString())
                .withProperty("keyid", Employee::id)
                .withProperty("keyname", Employee::name)
                .withProperty("keyaddress", Employee::address);
    }

}
