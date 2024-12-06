package com.tech45degree.view;

import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Route("user/:userID?([0-9]{1,9})")
public class UserView extends VerticalLayout implements
        BeforeEnterObserver
        //HasUrlParameter<String>
{

    public UserView(){
        add(new H3("User View"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Optional<String> optionalUserID = beforeEnterEvent.getRouteParameters().get("userID");
        Optional<String> optionalPhone = beforeEnterEvent.getRouteParameters().get("phone");

        NotificationUtil.showSuccessNotification(optionalUserID.orElse(""));
        NotificationUtil.showSuccessNotification(optionalPhone.orElse(""));

    }

    /*
    @Override
    public void setParameter(BeforeEvent beforeEvent,@OptionalParameter  String parameter) {




        /*
        // FOR ROUTE PARAM
         NotificationUtil.showSuccessNotification(parameter);
        */



        /*
        // FOR QUERY PARAM
        QueryParameters queryParameters = beforeEvent.getLocation().getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();

        List<String> names = parametersMap.get("name");

        List<String> phone = parametersMap.get("phone");

        List<String> email = parametersMap.get("email");

        if(null != names && !names.isEmpty()) {
            NotificationUtil.showSuccessNotification(names.get(0));
        }

        if(null != phone && !phone.isEmpty()) {
            NotificationUtil.showSuccessNotification(phone.get(0));
        }

        if(null != email && !email.isEmpty()) {
            NotificationUtil.showSuccessNotification(email.get(0));
        }

        }
       */

}
