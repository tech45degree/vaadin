package com.tech45degree.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("textfield")
public class TextFieldView extends VerticalLayout {

    TextFieldView(){

        Icon icon = new Icon(VaadinIcon.DOLLAR);
        icon.setColor("green");

        TextField txtName = new TextField();
        txtName.setLabel("Amount : ");
        //txtName.setValue("Jhon");
        txtName.setRequired(true);
        txtName.setPlaceholder("Please insert your Amount");
        txtName.setHelperText("Amount should be between 1 to 2000");
        txtName.setMinLength(5);
        txtName.setMaxLength(20);

        txtName.setEnabled(true);
        txtName.setErrorMessage("Amount should be between 1 to 2000");
      //  txtName.setTooltipText("Please insert your first name");

        txtName.setClearButtonVisible(true);
        //txtName.setPattern("^[A-Za-z0-9]*$");
        //txtName.setAllowedCharPattern("^[A-Za-z0-9]*$");

       // txtName.setValueChangeMode(ValueChangeMode.EAGER);

        txtName.setPrefixComponent(icon);
        txtName.setWidth("300px");

        txtName.addValueChangeListener(event -> {
           // Notification.show(event.getValue());
        });

        txtName.addKeyDownListener(event -> {
          //  Notification.show(event.getKey().toString());
        });


       // txtName.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD,TextFieldVariant.LUMO_SMALL,TextFieldVariant.LUMO_ALIGN_RIGHT);

    /*    txtName.getStyle().set("background","violet");
        txtName.getStyle().set("font-size","12px");
        txtName.getStyle().set("font-weight","bold");
        txtName.getStyle().set("font-style","italic");
        txtName.getStyle().set("border-radius","8px");*/

        txtName.addClassName("my-custom-textfield");

        add(txtName);

    }
}
