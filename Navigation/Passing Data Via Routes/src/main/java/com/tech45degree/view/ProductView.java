package com.tech45degree.view;

import com.tech45degree.util.NotificationUtil;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("product")
public class ProductView extends VerticalLayout implements HasUrlParameter<String> {

    public ProductView(){
        add(new H3("Product View"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent,@WildcardParameter String productID) {

        if(productID != null){
            NotificationUtil.showSuccessNotification(productID);
        }else{
            NotificationUtil.showErrorNotification(productID);
        }

    }
}
