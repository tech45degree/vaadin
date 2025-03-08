package com.tech45degree.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("accordion")
public class AccordionView extends VerticalLayout{


    AccordionView(){

        Accordion accordionForm =  new Accordion();
        accordionForm.add("Personal Form",createPersonalForm());

        add(accordionForm);

        Accordion accordionDetails =  new Accordion();
        accordionDetails.add("Personal Details",createPersonalDetails());

        add(accordionDetails);


        AccordionPanel accordionPanelForm = new AccordionPanel();
        accordionPanelForm.setSummaryText("Personal Form");
        accordionPanelForm.add(createPersonalForm());
       // accordionPanelForm.setEnabled(false);
        accordionPanelForm.addOpenedChangeListener(openedChangeEvent -> {
            if(openedChangeEvent.isOpened()) {
                Notification.show("Personal Form");
            }
        });

        AccordionPanel accordionPanelDetails = new AccordionPanel();
        accordionPanelDetails.setSummary(new Span("Personal Details"));
        accordionPanelDetails.add(createPersonalDetails());
        accordionPanelDetails.setTooltipText("This panel contains personal details");

        Accordion accordionPersonal = new Accordion();
        accordionPersonal.add(accordionPanelForm);
        accordionPersonal.add(accordionPanelDetails);

        accordionPersonal.addClassName("personal-accordion");

        add(accordionPersonal);

        Accordion accordionLinks  = new Accordion();

        AccordionPanel panelServices = new AccordionPanel();
        panelServices.setSummaryText("Services");
        panelServices.add(createContent(createStyledAnchor("#","Consultation"),
                createStyledAnchor("#","Product Development"),
                createStyledAnchor("#","Cloud Services")));

        AccordionPanel panelAbout= new AccordionPanel();
        panelAbout.setSummaryText("About");
        panelAbout.add(createContent(createStyledAnchor("#","About Us"),
                createStyledAnchor("#","Contact Us")));

        AccordionPanel panelPortfolio = new AccordionPanel();
        panelPortfolio.setSummaryText("Portfolio");
        panelPortfolio.add(createContent(createStyledAnchor("#","Content Management System"),
                createStyledAnchor("#","Document Management System"),
                createStyledAnchor("#","Learning Management System")));

        accordionLinks.add(panelServices);
        accordionLinks.add(panelAbout);
        accordionLinks.add(panelPortfolio);
        accordionLinks.open(panelAbout);

        add(accordionLinks);

    }


    private FormLayout createPersonalForm(){

        TextField txtFirstName = new TextField("First Name");
        TextField txtLastName = new TextField("Last Name");
        TextField txtMobile = new TextField("Mobile");
        TextArea txtAddress = new TextArea("Address");
        Button btnSave = new Button("Save");
        Button btnReset = new Button("Reset");

        FormLayout layoutPersonalForm = new FormLayout();
        layoutPersonalForm.add(txtFirstName,txtLastName,txtMobile,txtAddress,btnSave,btnReset);

        layoutPersonalForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0",2));

        return layoutPersonalForm;

    }

    private FormLayout createPersonalDetails(){

        H3 lblName = new H3("Jhon");
        H3 lblMobile = new H3("+01102932839");
        H3 lblAddress = new H3("Street Tahlia, King Road, Mars");

        FormLayout layoutDetailsForm = new FormLayout();
        layoutDetailsForm.add(lblName,lblMobile,lblAddress);

        layoutDetailsForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));

        return layoutDetailsForm;

    }

    private FormLayout createFinancialDetails(){

        H3 lblAccountTitle = new H3("Name: Jhon");
        H3 lblAccount = new H3("Account: SA4384383767347434");
        H3 lblBank = new H3("Bank XYZ, Mars");

        FormLayout layoutDetailsForm = new FormLayout();
        layoutDetailsForm.add(lblAccountTitle,lblAccount,lblBank);

        layoutDetailsForm.setResponsiveSteps(new FormLayout.ResponsiveStep("0",1));

        return layoutDetailsForm;

    }

    private VerticalLayout createContent(Anchor... anchors) {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        content.setSpacing(false);
        content.add(anchors);

        return content;
    }

    private Anchor createStyledAnchor(String href, String text) {
        Anchor anchor = new Anchor(href, text);
        anchor.getStyle().set("color", "var(--lumo-primary-text-color)");
        anchor.getStyle().set("text-decoration", "none");

        return anchor;
    }

}
