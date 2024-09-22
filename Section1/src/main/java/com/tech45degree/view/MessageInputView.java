package com.tech45degree.view;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.boot.devtools.livereload.LiveReloadServer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Route("messageinput")
public class MessageInputView extends VerticalLayout {

    MessageList messageList = null;

    MessageInputView(){

        MessageInput messageInput = new MessageInput();


        messageInput.addSubmitListener(submitEvent -> {
            //Notification.show(submitEvent.getValue());

            Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);

            MessageListItem message1 = new MessageListItem(submitEvent.getValue(),now,"Jhon");
            message1.setUserColorIndex(1);
            message1.setUserAbbreviation("JJ");

            List<MessageListItem> messageListItems = new ArrayList<>(messageList.getItems());
            messageListItems.add(message1);

            messageList.setItems(messageListItems);

        });




        add(createMessageList(),messageInput);
    }


    private VerticalLayout createMessageList(){

        VerticalLayout verticalLayout = new VerticalLayout();

        messageList = new MessageList();

        Instant yesterday = LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC);

        MessageListItem message1 = new MessageListItem("Hello Mike. Did you check the order?",yesterday,"Jhon");
        message1.setUserColorIndex(1);
        message1.setUserAbbreviation("JJ");


        Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);

        MessageListItem message2 = new MessageListItem("Yes Jhon i checked the order, it is good.",now,"Mike","images/1.png");
        message2.setUserColorIndex(2);


        messageList.setItems(message1,message2);

        verticalLayout.add(messageList);
        return verticalLayout;
    }
}
