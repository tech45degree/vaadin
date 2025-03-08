package com.tech45degree.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalTime;

@Route("timepicker")
public class TimePickerView extends VerticalLayout {

    TimePickerView(){

        TimePicker currentTime = new TimePicker("Current Time");
        //currentTime.setStep(Duration.ofMinutes(30));
        //currentTime.setMin(LocalTime.of(4,00));
       // currentTime.setMax(LocalTime.of(5,00));
        currentTime.setErrorMessage("Invalid Time");

        currentTime.setValue(LocalTime.of(4,35));
        currentTime.setClearButtonVisible(true);


        add((currentTime));
    }
}
