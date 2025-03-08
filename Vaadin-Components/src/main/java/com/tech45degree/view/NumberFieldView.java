package com.tech45degree.view;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("numberfield")
public class NumberFieldView extends VerticalLayout {

    NumberFieldView(){


        NumberField nbPrice = new NumberField("Price");
        nbPrice.setErrorMessage("Price should be in double only");
        nbPrice.setStepButtonsVisible(true);
        nbPrice.setMax(20);
        nbPrice.setMin(-4);
        nbPrice.setStep(2);

       // nbPrice.setEnabled(true);
      //  nbPrice.setVisible(true);

/*        Div divSmiley =  new Div(":)");
        divSmiley.getStyle().set("color","orange");

        nbPrice.setPrefixComponent(divSmiley);*/

        Icon dollarIcon = new Icon(VaadinIcon.DOLLAR);
        dollarIcon.setColor("red");

        nbPrice.setSuffixComponent(dollarIcon);
/*
        nbPrice.setValueChangeMode(ValueChangeMode.EAGER);
       nbPrice.addValueChangeListener(event -> {
            Notification.show(event.getOldValue().toString());
        });

        nbPrice.addFocusListener(event -> {
            Notification.show("Focus listener called");
        });

        nbPrice.addBlurListener(event -> {
            Notification.show("Blur listener called");
        });*/


        Span h6HelperMessage = new Span("It will accept only two decimals i.e 123.56");

        nbPrice.setHelperComponent(h6HelperMessage);

        //nbPrice.addThemeVariants(TextFieldVariant.LUMO_SMALL,TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);

      //  nbPrice.getStyle().setBackgroundColor("beige");
      //  nbPrice.getStyle().setColor("green");
      //  nbPrice.getStyle().set("border-radius","8px");

        nbPrice.addClassName("price");

        add(nbPrice);


    }
}
