package com.tech45degree.views.chat;

import com.tech45degree.service.RAGService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Video Upload")
@Route("video")
@Menu(order = 0, icon = LineAwesomeIconUrl.UPLOAD_SOLID, title = "Upload Video")
public class UploadVideoView extends VerticalLayout {

    @Autowired
    private RAGService ragService;

    public UploadVideoView() {
        setSizeFull();
        createUploadForm();
    }

    private void createUploadForm() {
        addClassNames("upload-view", Width.FULL, Display.FLEX, Flex.AUTO);
        setSpacing(false);
        setSizeFull();

        TextField txtVideoPath = new TextField("YouTube Video ID");

        HorizontalLayout hlButtons = new HorizontalLayout();

        Button btnUpload = new Button("Upload");
        btnUpload.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        btnUpload.addClickListener(e -> {
            try{

                String response = ragService.uploadVideo(txtVideoPath.getValue());

                Notification notification = new Notification(response);
                notification.open();
                notification.setDuration(3000);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY, NotificationVariant.LUMO_SUCCESS);

            }catch (Exception ex){
                Notification notification = new Notification(ex.getMessage());
                notification.open();
                notification.setDuration(3000);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY, NotificationVariant.LUMO_ERROR);
            }
        });

        Button btnCancel = new Button("Cancel", event -> txtVideoPath.clear());
        btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

        hlButtons.add(btnUpload, btnCancel);


        VerticalLayout mainLayout = new VerticalLayout(txtVideoPath, hlButtons);
        mainLayout.setSizeFull();
        mainLayout.setSpacing(true);
        mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(mainLayout);
    }
}

