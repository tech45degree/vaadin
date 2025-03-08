package com.tech45degree.util;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class CustomStringDoubleConverter implements Converter<String, Double> {
    @Override
    public Result<Double> convertToModel(String stringValue, ValueContext valueContext) {

        try{
            return Result.ok(Double.parseDouble(stringValue));
        }catch (Exception e){
            return Result.error("Must be double value");
        }

    }

    @Override
    public String convertToPresentation(Double doubleValue, ValueContext valueContext) {
        return String.valueOf(doubleValue);
    }
}
