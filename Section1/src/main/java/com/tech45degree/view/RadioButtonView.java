package com.tech45degree.view;

import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.Route;

@Route("radiobutton")
public class RadioButtonView extends VerticalLayout {

    RadioButtonView(){
        RadioButtonGroup<String> rdbtnPermission = new RadioButtonGroup<>();
        rdbtnPermission.setLabel("Permission");
        rdbtnPermission.setItems(DataGenerator.getPermissions());
        rdbtnPermission.setItemEnabledProvider(item ->
        {
            return !item.equalsIgnoreCase("Edit");
        });

        rdbtnPermission.setValue("Read");
        rdbtnPermission.setHelperText("Select Permission");
      //  rdbtnPermission.setEnabled(false);
        rdbtnPermission.addClassName("radio-permission");

        rdbtnPermission.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });

        RadioButtonGroup<Employee> rdbtnEmployee = new RadioButtonGroup<>();
        rdbtnEmployee.setLabel("Employee");
        rdbtnEmployee.setItems(DataGenerator.getEmployees());
        rdbtnEmployee.setHelperText("Select Employee");
        rdbtnEmployee.setItemLabelGenerator(employee -> employee.name()+" - "+employee.profession());
      //  rdbtnEmployee.setReadOnly(true);

      //  rdbtnEmployee.addThemeVariants(RadioGroupVariant.LUMO_HELPER_ABOVE_FIELD,RadioGroupVariant.LUMO_VERTICAL);


        rdbtnEmployee.addValueChangeListener(event -> {
            Notification.show(event.getValue().name()+" - "+event.getValue().profession());
        });

        add(rdbtnPermission,rdbtnEmployee);
    }
}
