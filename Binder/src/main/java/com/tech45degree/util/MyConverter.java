package com.tech45degree.util;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class MyConverter implements Converter<String, Integer> {
    @Override
    public Result<Integer> convertToModel(String s, ValueContext valueContext) {
       try{

           return Result.ok(Integer.parseInt(s));
       }catch (NumberFormatException e){

           return Result.error("Must be number");
       }
    }

    @Override
    public String convertToPresentation(Integer integer, ValueContext valueContext) {
        return String.valueOf(integer);
    }
}
