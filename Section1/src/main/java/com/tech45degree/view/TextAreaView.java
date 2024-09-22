package com.tech45degree.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("textarea")
public class TextAreaView extends VerticalLayout {

    TextAreaView(){

        TextArea txtDescription = new TextArea("Description");
        txtDescription.setMinLength(4);
        txtDescription.setMaxLength(1000);
        txtDescription.setPlaceholder("Description");
        txtDescription.setErrorMessage("Description should be between 4 to 70 in length");
        txtDescription.setWidth("100%");

      //  txtDescription.setValue("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    //    txtDescription.setReadOnly(true);
        txtDescription.setValueChangeMode(ValueChangeMode.EAGER);

        txtDescription.addValueChangeListener(event -> {
           event.getSource().setHelperText(event.getValue().length() +" / "+1000);
        });

        txtDescription.setClearButtonVisible(true);

     //   txtDescription.addThemeVariants(TextAreaVariant.LUMO_ALIGN_RIGHT,TextAreaVariant.LUMO_HELPER_ABOVE_FIELD);
   //     txtDescription.getStyle().set("color","red");
    //    txtDescription.getStyle().setBackgroundColor("biege");

        add(txtDescription);
    }
}
