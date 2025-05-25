package com.tech45degree.views.chat;

import com.tech45degree.service.RAGService;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("RAG Chat")
@Route("")
@Menu(order = 1, icon = LineAwesomeIconUrl.COMMENTS, title = "RAG")
public class ChatWithPDFView extends VerticalLayout {

    @Autowired
    private RAGService ragService;

    public ChatWithPDFView() {
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

        ProgressBar progressBar = new ProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        progressBar.addThemeVariants(ProgressBarVariant.LUMO_SUCCESS);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf"); // Accept only PDF

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

            CompletableFuture.supplyAsync(() -> ragService.queryRAG(userMessageText))
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

        VerticalLayout chatLayout = new VerticalLayout(progressBar, list, input);
        chatLayout.setSizeFull();
        chatLayout.expand(list);
        add(chatLayout);
    }
}
