package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("horizontallayout")
public class HorizontalLayoutView extends Div {

    HorizontalLayoutView() {
        Button btnLeft = new Button("Left Button");
        Button btnCenter = new Button("Center Button");
        Button btnRight = new Button("Right Button");

        HorizontalLayout hlayoutprimary = new HorizontalLayout();
        hlayoutprimary.setHeight("500px");
        hlayoutprimary.setWidth("750px");
      //  hlayoutprimary.setSpacing(false);
      //  hlayoutprimary.setMargin(true);
      //  hlayoutprimary.setPadding(true);

        hlayoutprimary.setAlignItems(FlexComponent.Alignment.CENTER);
       // hlayoutprimary.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
       // hlayoutprimary.setVerticalComponentAlignment(FlexComponent.Alignment.START,btnLeft);
       // hlayoutprimary.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER,btnCenter);
       // hlayoutprimary.setVerticalComponentAlignment(FlexComponent.Alignment.END,btnRight);

        hlayoutprimary.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

      //  hlayoutprimary.setFlexGrow(1,btnCenter);

        hlayoutprimary.add(btnLeft, btnCenter, btnRight);

      //  hlayoutprimary.addClassName("hlPrimary");

     //   hlayoutprimary.getStyle().set("background","dimgray");

        add(hlayoutprimary);
    }
}
