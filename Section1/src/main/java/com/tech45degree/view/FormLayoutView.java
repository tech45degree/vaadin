package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("formlayout")
public class FormLayoutView extends VerticalLayout {

    FormLayoutView(){

        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtAddress = new TextField("Address");
        TextField txtPhone = new TextField("Phone");

        Button btnSubmit = new Button("Submit");
        Button btnReset = new Button("Reset");

        FormLayout formLayout = new FormLayout();

 /*       formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("200px",2),
                new FormLayout.ResponsiveStep("500px",4),
                new FormLayout.ResponsiveStep("1000px",6));*/

       // formLayout.setColspan(txtAddress,4);

        formLayout.addFormItem(txtFirstName,"First Name");
        formLayout.addFormItem(txtLastName,"Last Name");

    //    formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0px",5));


        formLayout.add(txtAddress,txtPhone,btnSubmit,btnReset);

       // formLayout.setEnabled(false);

     //   formLayout.addClassName("flPrimary");


        add(formLayout);

    }
}
