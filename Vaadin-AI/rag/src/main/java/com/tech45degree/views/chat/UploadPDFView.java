package com.tech45degree.views.chat;

import com.tech45degree.service.RAGService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("PDF Upload")
@Route("pdf")
@Menu(order = 0, icon = LineAwesomeIconUrl.UPLOAD_SOLID, title = "Upload PDF")
public class UploadPDFView extends VerticalLayout {

    @Autowired
    private RAGService ragService;

    private String uploadedFilePath = "";

    public UploadPDFView() {
        setSizeFull();
        createUploadForm();
    }

    private void createUploadForm() {
        addClassNames("upload-view", Width.FULL, Display.FLEX, Flex.AUTO);
        setSpacing(false);
        setSizeFull();

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf"); // Accept only PDF

        upload.addSucceededListener(event -> {
            try {
                String fileName = event.getFileName();
                InputStream inputStream = buffer.getInputStream();
                File targetFile = new File("src/main/resources/" + fileName);
                targetFile.getParentFile().mkdirs();
                Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                uploadedFilePath = fileName;

                String response = ragService.uploadPDF(uploadedFilePath);

                Notification notification = new Notification(response);
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY, NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.setDuration(3000);
                notification.open();

            } catch (Exception e) {
                e.printStackTrace();

                Notification notification = new Notification(e.getMessage());
                notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY, NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.setDuration(3000);
                notification.open();

            }
        });


        VerticalLayout mainLayout = new VerticalLayout(upload);
        mainLayout.setSizeFull();
        add(mainLayout);
    }
}

