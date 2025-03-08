package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.dialog.DialogVariant;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("dialog")
public class DialogView extends VerticalLayout {

    Dialog dialog;

    DialogView(){

        dialog = new Dialog();

        dialog.setModal(true);
        dialog.setResizable(true);
        dialog.setCloseOnEsc(true);
        dialog.setDraggable(true);


        dialog.add(dialogContent());
        dialog.setHeaderTitle("About Us");

        dialog.addThemeVariants(DialogVariant.LUMO_NO_PADDING);

        Button btnSave = new Button("Save");
        Button btnReset = new Button("Reset");

        dialog.getFooter().add(btnSave,btnReset);

        Button btnShow = new Button("Show Dialog");

        btnShow.addClickListener(event -> {
            dialog.open();
        });

        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setHeader("About Us");
        confirmDialog.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        confirmDialog.setRejectable(true);
        confirmDialog.setCancelable(true);

        confirmDialog.setCloseOnEsc(true);

        confirmDialog.setConfirmText("Are you sure to confirm?");
        confirmDialog.setCancelText("Cancel This");
        confirmDialog.setRejectText("Reject This");

        confirmDialog.setCancelButton("Cancel By Button",event -> {
            Notification.show("Cancel by Button");
        });

        Button btnShowConfirmDialog = new Button("Show Confirm Dialog");
        btnShowConfirmDialog.addClickListener(buttonClickEvent -> {
            confirmDialog.open();
        });

        confirmDialog.addConfirmListener(event -> {
            Notification.show("Are you sure to confirm?");
        });

        confirmDialog.addRejectListener(event -> {
            Notification.show("Rejected");
        });

/*        confirmDialog.addCancelListener(event -> {
            Notification.show("Cancelled");
        });*/




        add(btnShow,btnShowConfirmDialog);

    }


    private VerticalLayout dialogContent(){

        VerticalLayout verticalLayout = new VerticalLayout();

        H6 txtIpsum = new H6("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        Button btnClose = new Button(VaadinIcon.CLOSE.create());

        btnClose.addClickListener(x -> {
            dialog.close();
        });

        verticalLayout.add(btnClose,txtIpsum);
        verticalLayout.setHorizontalComponentAlignment(Alignment.END,btnClose);

        return verticalLayout;

    }
}
