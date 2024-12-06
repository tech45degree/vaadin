package com.tech45degree.exception;


import com.tech45degree.view.HomeView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;

public class RouteNotFound extends VerticalLayout implements HasErrorParameter<NotFoundException> {
    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent,
                                 ErrorParameter<NotFoundException> errorParameter) {
       // NotificationUtil.showErrorNotification("Could not navigate to '"+beforeEnterEvent.getLocation().getPath()+"'");

        beforeEnterEvent.forwardTo(HomeView.class);
        return 404;
    }
}
