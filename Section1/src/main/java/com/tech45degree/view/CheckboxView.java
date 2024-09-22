package com.tech45degree.view;


import com.tech45degree.model.Employee;
import com.tech45degree.util.DataGenerator;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.frontend.NodeTasks;

import java.util.HashSet;

@Route("checkbox")
public class CheckboxView extends VerticalLayout {


    CheckboxView(){

        Checkbox chkBxAgree = new Checkbox();
        chkBxAgree.setLabel("I agree to terms and conditions");
        chkBxAgree.setValue(false);
        chkBxAgree.addClassName("checkbox-agree");


        CheckboxGroup<Employee> chkbxEmployee = new CheckboxGroup();
        chkbxEmployee.setLabel("Employee");
        chkbxEmployee.setItems(DataGenerator.getEmployees());
        chkbxEmployee.setItemLabelGenerator(emp -> emp.name() +" "+ emp.profession());
        chkbxEmployee.select(DataGenerator.getEmployees().get(1),DataGenerator.getEmployees().get(3));
        chkbxEmployee.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
       // chkbxEmployee.setReadOnly(true);
       // chkbxEmployee.setEnabled(false);

        chkbxEmployee.addClassName("checkbox-employee");

        Checkbox chkSelectAll = new Checkbox();
        chkSelectAll.setLabel("Select All");
        chkSelectAll.setIndeterminate(true);


        HashSet<String> selectedWeekdays = new HashSet<>();
        selectedWeekdays.add("SUN");
        selectedWeekdays.add("MON");

        CheckboxGroup<String>  checkBoxWeekdays = new CheckboxGroup<>();
        checkBoxWeekdays.setLabel("Weekdays");
        checkBoxWeekdays.setItems(DataGenerator.getWeekdaysMap().keySet());
        checkBoxWeekdays.select(selectedWeekdays);

        checkBoxWeekdays.setItemLabelGenerator(weekday -> DataGenerator.getWeekdaysMap().get(weekday));

        checkBoxWeekdays.addValueChangeListener(event -> {
           // Notification.show(event.getValue().toString());

            if(event.getValue().size() == DataGenerator.getWeekdaysMap().size()){
                chkSelectAll.setValue(true);
                chkSelectAll.setIndeterminate(false);
            }else if(event.getValue().isEmpty()){
                chkSelectAll.setValue(false);
                chkSelectAll.setIndeterminate(false);
            }else{
                chkSelectAll.setIndeterminate(true);
            }

        });


        chkSelectAll.addValueChangeListener(event -> {
            if(event.getValue()){
                checkBoxWeekdays.select(DataGenerator.getWeekdaysMap().keySet());
            }else{
                checkBoxWeekdays.deselectAll();
            }
        });

        add(chkBxAgree,chkbxEmployee,chkSelectAll,checkBoxWeekdays);

    }



}
