package com.tech45degree.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.DatePickerVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Route("datepicker")
public class DatePickerView extends HorizontalLayout {


    DatePickerView(){

        setPadding(true);


        DatePicker dfDOB = new DatePicker("Date Of Birth");
       // dfDOB.setMin(LocalDate.now());
       // dfDOB.setMax(LocalDate.now().plusDays(6));

        dfDOB.setWeekNumbersVisible(true);
        dfDOB.setI18n(new DatePicker.DatePickerI18n().setFirstDayOfWeek(1));

        dfDOB.setInitialPosition(LocalDate.now(ZoneId.systemDefault()).with(TemporalAdjusters.lastDayOfMonth()));
        dfDOB.setAutoOpen(false);

        dfDOB.addValueChangeListener(event -> {
            Notification.show(event.getValue().toString());
        });


/*        DatePicker.DatePickerI18n germanI18n = new DatePicker.DatePickerI18n();
        germanI18n.setMonthNames(List.of("Januar", "Februar", "März", "April",
                "Mai", "Juni", "Juli", "August", "September", "Oktober",
                "November", "Dezember"));
        germanI18n.setWeekdays(List.of("Sonntag", "Montag", "Dienstag",
                "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
        germanI18n.setWeekdaysShort(
                List.of("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
        germanI18n.setToday("Heute");
        germanI18n.setCancel("Abbrechen");

        dfDOB.setI18n(germanI18n);*/

       // dfDOB.setLocale(new Locale("fi","FI"));
        dfDOB.addClassName("dob-datepicker");
        dfDOB.setOverlayClassName("dob-datepicker-overlay");



        add(dfDOB);

    }
}
