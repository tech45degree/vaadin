package com.tech45degree.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

@Route("email")
public class EmailFieldView extends VerticalLayout {

    EmailFieldView(){

        EmailField email = new EmailField("Email");
        email.setTooltipText("It should contain correct email");
        email.setErrorMessage("Email is invalid");

        email.setRequired(true);
        email.setWidth("100%");
        email.setValue("jhon@gmail.com");

        add(email);
    }
}
