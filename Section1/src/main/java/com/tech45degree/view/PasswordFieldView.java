package com.tech45degree.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("password")
public class PasswordFieldView extends VerticalLayout {

       Span  passwordStrengthText = null;

        PasswordFieldView(){
            int maxChar = 8;
            PasswordField passwordField = new PasswordField("Password");

            passwordField.setMaxLength(maxChar);
            passwordField.setMinLength(0);
           // passwordField.setPattern("^[A-Za-z0-9]*$");
            passwordField.setAllowedCharPattern("^[A-Za-z0-9]*$");
            passwordField.setClearButtonVisible(true);
            passwordField.setRevealButtonVisible(true);

            passwordField.setErrorMessage("Password can contain only alphanumeric characters");

            passwordField.setValueChangeMode(ValueChangeMode.EAGER);
            passwordField.addValueChangeListener(event -> {
               // event.getSource().setHelperText("char :" +event.getValue().length()+ " / "+maxChar);
                updatePasswordStrength(event.getValue().length());
            });



            Div passwordStrength = new Div();
            passwordStrengthText = new Span("Weak");
            passwordStrengthText.getStyle().set("color","red");
            passwordStrengthText.getStyle().set("font-weight","bolder");
            passwordStrength.add(new H6("Password Strength: "),passwordStrengthText);


            passwordField.setHelperComponent(passwordStrength);

           // passwordField.addThemeVariants(TextFieldVariant.LUMO_SMALL,TextFieldVariant.LUMO_HELPER_ABOVE_FIELD,TextFieldVariant.LUMO_ALIGN_RIGHT);

           // passwordField.getStyle().set("background","beige");
           // passwordField.getStyle().set("color","green");

            passwordField.addClassName("customPassword");

            add(passwordField);


        }


    private void updatePasswordStrength(int length){

        if(length >= 4 && length < 6){
            passwordStrengthText.setText("Medium");
            passwordStrengthText.getStyle().set("color","orange");

        } else if (length >=6) {
            passwordStrengthText.setText("High");
            passwordStrengthText.getStyle().set("color","green");

        }else{
            passwordStrengthText.setText("Weak");
            passwordStrengthText.getStyle().set("color","red");

        }

    }


}


