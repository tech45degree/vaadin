package com.tech45degree.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;



@Route
public class HomeView extends VerticalLayout implements BeforeLeaveObserver {

    public HomeView(){
        add(new H3("Home"));

        Button btnContactUs = new Button("Contact us");

        btnContactUs.addClickListener(e -> {
            UI.getCurrent().navigate(ContactUsView.class);
        });


        Button btnAboutUs = new Button("About us");

        btnAboutUs.addClickListener(e -> {
            UI.getCurrent().navigate("aboutUs");
        });


        Anchor anchorAboutUs = new Anchor("aboutUs","About Us");
        anchorAboutUs.getElement().setAttribute("target", "_blank");

        add(btnContactUs,btnAboutUs,anchorAboutUs);

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

        BeforeLeaveEvent.ContinueNavigationAction action =
                beforeLeaveEvent.postpone();


        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setText("Your form has changes! Are you sure you want to leave?");
        confirmDialog.setCancelable(true);
        confirmDialog.addConfirmListener(event -> action.proceed());
        confirmDialog.addCancelListener(event -> action.cancel());
        confirmDialog.open();

    }
}
