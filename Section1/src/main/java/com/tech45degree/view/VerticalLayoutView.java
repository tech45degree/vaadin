package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("verticallayout")
public class VerticalLayoutView extends Div {

    VerticalLayoutView(){

        Button btnTop = new Button("Top Button");
        Button btnCenter = new Button("Center Button");
        Button btnBottom = new Button("Bottom Button");

        VerticalLayout vlayoutprimary = new VerticalLayout();
        vlayoutprimary.add(btnTop,btnCenter,btnBottom);

       // vlayoutprimary.setSpacing(false);
      //  vlayoutprimary.setPadding(false);
       // vlayoutprimary.setMargin(true);

        vlayoutprimary.setHeight("500px");

      //  vlayoutprimary.setAlignItems(FlexComponent.Alignment.END);
          vlayoutprimary.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

     //   vlayoutprimary.setHorizontalComponentAlignment(FlexComponent.Alignment.START,btnTop);
    //    vlayoutprimary.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,btnCenter);
    //    vlayoutprimary.setHorizontalComponentAlignment(FlexComponent.Alignment.END,btnBottom);

      //  vlayoutprimary.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);

     //   vlayoutprimary.setFlexGrow(0.5,btnTop);
     //   vlayoutprimary.setFlexGrow(1,btnCenter);
     //   vlayoutprimary.setFlexGrow(0.5,btnBottom);

      //  vlayoutprimary.expand(btnCenter);


      //  vlayoutprimary.getStyle().set("background","lightgray");

      //  vlayoutprimary.addClassName("vlPrimary");


        // vlayoutprimary.setEnabled(false);

        VerticalLayout vLayoutSecondary = new VerticalLayout();

        TextField txtName = new TextField("Name");

        vLayoutSecondary.add(txtName);
       // vLayoutSecondary.setVisible(false);


        add(vlayoutprimary,vLayoutSecondary);
    }
}
