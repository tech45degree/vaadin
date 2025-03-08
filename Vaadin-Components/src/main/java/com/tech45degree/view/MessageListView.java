package com.tech45degree.view;

import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Route("messagelist")
public class MessageListView extends VerticalLayout {

    MessageListView(){

        MessageList messageList = new MessageList();


        Instant yesterday = LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC);

        MessageListItem message1 = new MessageListItem("Hello Mike. Did you check the order?",yesterday,"Jhon");
        message1.setUserAbbreviation("J");
        message1.setUserColorIndex(1);
        message1.addClassNames("msg1");



        Instant now = LocalDateTime.now().toInstant(ZoneOffset.UTC);

        MessageListItem message2 = new MessageListItem("Yes Jhon i checked the order, it is good.",now,"Mike","images/1.png");
        message2.setUserColorIndex(2);

        messageList.setItems(message1,message2);

        add(messageList);
    }
}
