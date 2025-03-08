package com.tech45degree.view;

import com.tech45degree.model.Person;
import com.tech45degree.util.CustomStringDoubleConverter;
import com.tech45degree.util.MyConverter;
import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;

@Route("/")
public class PersonView extends VerticalLayout {


    public PersonView() {
        setSizeFull();
        createForm();
    }

    private void createForm() {


        FormLayout formLayout = new FormLayout();
        formLayout.setWidth("100%");
        formLayout.setHeight("100%");

        TextField txtName = new TextField("Name");
        TextField txtMobile = new TextField("Mobile");
        TextField txtAddress = new TextField("Address");
        TextField txtEmail = new TextField("Email");
        TextField txtAge = new TextField("Age");

        TextField txtAmount = new TextField("Amount");

        Binder<Person> binder = new Binder<>(Person.class);

        binder.forField(txtName)
                .withValidator(name -> name.length()>6,"Name should be greater than 6 characters")
                .bind(Person::getName, Person::setName);

        binder.forField(txtMobile).bind(Person::getMobile, Person::setMobile);
        binder.forField(txtAddress)
                .asRequired("Address is required")
                .bind(Person::getAddress, Person::setAddress);

        binder.forField(txtEmail)
                .withValidator(new EmailValidator("Email is invalid"))
                .bind(Person::getEmail, Person::setEmail);

        binder.forField(txtAge)
                .withConverter(new MyConverter())
                .bind(Person::getAge,Person::setAge);

        binder.forField(txtAmount)
                .withConverter(new CustomStringDoubleConverter())
                .bind(Person::getAmount,Person::setAmount);

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");

        horizontalLayout.add(btnSave,btnCancel);

        formLayout.add(txtName,txtMobile, txtAge, txtAddress, txtEmail,txtAmount,horizontalLayout);

        add(formLayout);

        Person person = new Person();
        person.setName("Jhon etc etc");
        person.setMobile("987654321");
        person.setAddress("123 Main St");
        person.setEmail("jhon@gmail.com");
        person.setAge(25);

        binder.readBean(person);


        btnSave.addClickListener(e -> {


            try {
                if(binder.validate().isOk()) {
                    binder.writeBean(person);
                    NotificationUtil.showSuccessNotification(String.valueOf(person.getAmount()));

                }
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }

        });

    }
}
