package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Route("upload")
public class UploadView extends VerticalLayout {


    UploadView(){

       // MemoryBuffer buffer = new MemoryBuffer();

        MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();

        Upload  fileUpload = new Upload(multiFileMemoryBuffer);

        fileUpload.setAcceptedFileTypes(".png");
        fileUpload.setDropLabel(new H6("upload files by dropping here"));

        Button uploadButton  = new Button();
        uploadButton.setText("Upload");

        fileUpload.setUploadButton(uploadButton);
        fileUpload.setDropLabelIcon(VaadinIcon.UPLOAD_ALT.create());
        fileUpload.setDropAllowed(true);

        fileUpload.setMaxFiles(3);
        fileUpload.setMaxFileSize(1000);


        fileUpload.addClassName("image-upload");

        fileUpload.addSucceededListener(succeededEvent -> {
            Notification.show(succeededEvent.getFileName()+" is uploaded successfully");

            InputStream inputStream =  multiFileMemoryBuffer.getInputStream(succeededEvent.getFileName());

            File targetFile = new File("src/main/resources/"+succeededEvent.getFileName());

            try {
                FileUtils.copyInputStreamToFile(inputStream,targetFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        fileUpload.addFileRejectedListener(fileRejectedEvent -> {
            Notification.show(fileRejectedEvent.getErrorMessage());
        });

        fileUpload.addFailedListener(failedEvent -> {
            Notification.show(failedEvent.getFileName()+ " is failed to upload");
        });


        add(fileUpload);

    }

}
