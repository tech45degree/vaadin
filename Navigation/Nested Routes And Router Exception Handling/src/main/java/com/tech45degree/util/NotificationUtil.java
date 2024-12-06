package com.tech45degree.util;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class NotificationUtil {

    public static void showSuccessNotification(String text) {
        Notification notification = new Notification();
        notification.setText(text);
        notification.setDuration(5000);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }

    public static void showErrorNotification(String text) {
        Notification notification = new Notification();
        notification.setText(text);
        notification.setDuration(5000);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.open();
    }
}
