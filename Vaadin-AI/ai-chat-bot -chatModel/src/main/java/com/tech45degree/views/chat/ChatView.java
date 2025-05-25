package com.tech45degree.views.chat;

import com.tech45degree.service.OpenAIService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Chat")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.COMMENTS, title = "AI Chat Bot")
public class ChatView extends VerticalLayout {

    @Autowired
    private OpenAIService openAIService;

    private String uploadedFilePath = "";

    public ChatView() {
        setSizeFull();
        createChatForm();
    }

    private void createChatForm() {

        addClassNames("chat-view", Width.FULL, Display.FLEX, Flex.AUTO);
        setSpacing(false);
        setSizeFull();

        MessageList list = new MessageList();
        list.setWidthFull();

        MessageInput input = new MessageInput();
        input.setWidthFull();

        ProgressBar progressBar = new ProgressBar(); // Progress bar for AI task
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        progressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);

        // File Upload Component
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/png", "image/jpeg", "image/jpg");
        upload.addSucceededListener(event -> {
            try {
                String fileName = event.getFileName();
                InputStream inputStream = buffer.getInputStream();
                File targetFile = new File("src/main/resources/" + fileName);
                targetFile.getParentFile().mkdirs(); // Ensure directory exists
                Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                uploadedFilePath = fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        input.addSubmitListener(submitEvent -> {
            String userMessageText = submitEvent.getValue();

            MessageListItem userMessage = new MessageListItem(userMessageText, Instant.now(), "ME");
            userMessage.setUserColorIndex(6);
            userMessage.setUserAbbreviation("ME");

            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(userMessage);
            list.setItems(items);

            progressBar.setVisible(true);
            UI currentUI = UI.getCurrent();

            CompletableFuture.supplyAsync(() -> openAIService.sendMessage(userMessageText,uploadedFilePath))
                    .thenAccept(aiResponse -> {
                        currentUI.access(() -> {
                            MessageListItem aiMessage = new MessageListItem(aiResponse, Instant.now(), "AI");
                            aiMessage.setUserColorIndex(4);
                            aiMessage.setUserAbbreviation("AI");

                            List<MessageListItem> updatedItems = new ArrayList<>(list.getItems());
                            updatedItems.add(aiMessage);
                            list.setItems(updatedItems);

                            progressBar.setVisible(false);
                        });
                    })
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        currentUI.access(() -> progressBar.setVisible(false));
                        return null;
                    });
        });

        VerticalLayout chatLayout = new VerticalLayout(upload, progressBar, list, input);
        chatLayout.setSizeFull();
        chatLayout.expand(list);
        add(chatLayout);
    }
}
