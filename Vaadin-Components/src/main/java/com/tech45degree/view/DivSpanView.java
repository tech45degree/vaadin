package com.tech45degree.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("divspan")
public class DivSpanView extends VerticalLayout {

    DivSpanView(){

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        Button btn3 = new Button("Button 3");


        Div divprimary = new Div();
        divprimary.setTitle("Div Primary");
        divprimary.setText("This is div");
      //  divprimary.add(btn1,btn2);
        //divprimary.remove(btn2);
        //divprimary.removeAll();
      //  divprimary.replace(btn1,btn3);

        divprimary.getStyle().set("background","red");


        Span spanPrimary = new Span(btn1,btn2,btn3);



        add(divprimary,spanPrimary);


    }
}
