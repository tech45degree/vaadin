package com.tech45degree.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("splitlayout")
public class SplitLayoutView extends VerticalLayout {

    SplitLayoutView(){

        setHeightFull();

        SplitLayout splitLayout  = new SplitLayout();
        splitLayout.setWidthFull();
        splitLayout.setHeightFull();
        splitLayout.addToPrimary(primaryLayout());


        splitLayout.addClassName("primaryStyle");

     //   splitLayout.setPrimaryStyle("background","beige");
     //   splitLayout.setSecondaryStyle("background","red");


        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        splitLayout.setSplitterPosition(40);
        splitLayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);

        SplitLayout splitLayoutSecondary = new SplitLayout(new H1("Hello Tech45Degree on Secondary Splitter"),secondaryLayout());
        splitLayoutSecondary.setSizeFull();
        splitLayoutSecondary.setOrientation(SplitLayout.Orientation.VERTICAL);

     //   splitLayoutSecondary.setSecondaryStyle("background","blue");
        splitLayoutSecondary.addClassName("secondaryStyle");

        splitLayout.addToSecondary(splitLayoutSecondary);


        add(splitLayout);

    }

    private Component primaryLayout(){
        FormLayout primaryLayout = new FormLayout();

        TextField txtFirstName = new TextField("First Name");
        TextField txtLastName = new TextField("Last Name");
        TextField txtAddress = new TextField("Address");
        TextField txtPhone = new TextField("Phone");

        primaryLayout.add(txtFirstName,txtLastName,txtAddress,txtPhone);
        return primaryLayout;
    }

    private VerticalLayout secondaryLayout(){
        VerticalLayout verticalLayout = new VerticalLayout();

       TextArea txtDescription = new TextArea();
       txtDescription.setWidthFull();

       txtDescription.setValue("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
       verticalLayout.add(txtDescription);

        return verticalLayout;
    }
}
