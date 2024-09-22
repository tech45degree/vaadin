package com.tech45degree.view;

import com.tech45degree.model.Person;
import com.tech45degree.util.PersonDataProviderForLazyLoading;
import com.tech45degree.util.PersonFilterForLazyLoading;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("gridlazyloadingview")
public class GridLazyLoadingView extends VerticalLayout {



    public GridLazyLoadingView() {

        PersonFilterForLazyLoading personFilter = new PersonFilterForLazyLoading();

        PersonDataProviderForLazyLoading personDataProvider = new PersonDataProviderForLazyLoading();

        ConfigurableFilterDataProvider<Person,Void,PersonFilterForLazyLoading> configurableFilterDataProvider = personDataProvider.withConfigurableFilter();

        Grid<Person> gridPerson = new Grid<>(Person.class,false);

        gridPerson.addClassName("person-grid");

        gridPerson.setItems(configurableFilterDataProvider);
        gridPerson.setPageSize(20);
        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);

        Grid.Column<Person> columnFirstName = gridPerson.addColumn(Person::getFirstName).setFooter(createPersonCount(personDataProvider.getTotalCount()));
        Grid.Column<Person> columnLastName = gridPerson.addColumn(Person::getLastName);

        gridPerson.addColumn(Person::getAge,"age").setHeader("Age").setSortable(true);
        gridPerson.addColumn(Person::getEmail).setHeader("Email").setSortable(false).setResizable(true);

        gridPerson.addColumn(new ComponentRenderer<>(person -> {
            if (person.getGender().equalsIgnoreCase("Male")) {
                return VaadinIcon.MALE.create();
            } else {
                return VaadinIcon.FEMALE.create();
            }
        })).setHeader("Gender")
                .setComparator(Person::getGender)
                .setSortProperty("gender")
                .setWidth("5px")
                .setTooltipGenerator(person -> person.getGender());

        gridPerson.addColumn(Person::getSalary)
                .setHeader("Salary").setSortable(true)
                .setTextAlign(ColumnTextAlign.END)
                .setWidth("20px");

        gridPerson.addColumn(Person::getProfession,"profession").setHeader("Profession").setSortable(true).setResizable(true);

        HeaderRow headerRow = gridPerson.prependHeaderRow();
        headerRow.join(columnFirstName, columnLastName).setText("Full Name");



        TextField searchField = new TextField();
        searchField.setClearButtonVisible(true);
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        searchField.addValueChangeListener(event ->{
            personFilter.setSearchTerm(event.getValue());
            configurableFilterDataProvider.setFilter(personFilter);
        });


        add(searchField,gridPerson);

    }


    private static String createSalaryFooterText(List<Person> people) {
        double sumSalary = people.stream().mapToDouble(Person::getSalary).sum();

        return String.format("%s sum", sumSalary);
    }

    private static String createPersonCount(int totalRecords) {

        return String.format("%s total people", totalRecords);
    }

}
