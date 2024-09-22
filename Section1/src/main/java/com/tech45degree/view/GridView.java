package com.tech45degree.view;

import com.tech45degree.model.Person;
import com.tech45degree.util.DataGenerator;
import com.tech45degree.util.PersonFilter;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Route("gridview")
public class GridView extends VerticalLayout {

    public GridView() {

        Grid<Person> gridPerson = new Grid<>(Person.class,false);

      //  gridPerson.addThemeVariants(GridVariant.LUMO_ROW_STRIPES,GridVariant.LUMO_NO_BORDER,GridVariant.LUMO_NO_ROW_BORDERS);

        gridPerson.addClassName("person-grid");
       // gridPerson.setPageSize(3);
       // gridPerson.setHeight("800px");

        List<Person> people = DataGenerator.getPersons();
        GridListDataView<Person> dataView = gridPerson.setItems(people);



        Grid.Column<Person> columnFirstName = gridPerson.addColumn(Person::getFirstName).setFooter(createPersonCount(people));
        Grid.Column<Person> columnLastName = gridPerson.addColumn(Person::getLastName);

        gridPerson.addColumn(Person::getAge).setHeader("Age").setSortable(true);
        gridPerson.addColumn(Person::getEmail).setHeader("Email").setSortable(false).setResizable(true);
       // gridPerson.addColumn(Person::getGender).setHeader("Gender").setSortable(true);

        Grid.Column<Person> columnGender = gridPerson.addColumn(new ComponentRenderer<>(person -> {
            if (person.getGender().equalsIgnoreCase("Male")) {
                return VaadinIcon.MALE.create();
            } else {
                return VaadinIcon.FEMALE.create();
            }
        })).setHeader("Gender")
                .setComparator(Person::getGender)
                .setWidth("5px")
                .setTooltipGenerator(person -> person.getGender());

        Grid.Column<Person> columnSalary = gridPerson.addColumn(Person::getSalary)
                .setHeader("Salary").setSortable(true)
                .setTextAlign(ColumnTextAlign.END)
                .setWidth("20px")
                .setFooter(createSalaryFooterText(people));

        HeaderRow headerRow = gridPerson.prependHeaderRow();
        headerRow.join(columnFirstName, columnLastName).setText("Full Name");


       /* gridPerson.addColumn(createPersonRenderer())
                .setHeader("Person")
                .setComparator(Person::getFirstName)
                .setFrozen(true);*/


/*        gridPerson.getColumnByKey("profession").setSortable(false);
        gridPerson.getColumnByKey("gender").setHeader("Person Gender").setSortable(false);*/


        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);

        GridMultiSelectionModel<Person> selectionModel = (GridMultiSelectionModel<Person>)gridPerson.getSelectionModel();
        selectionModel.setDragSelect(true);

/*        gridPerson.addSelectionListener(event -> {
           // Notification.show(event.getAllSelectedItems().iterator().next().getFirstName());
            Notification.show(event.getAllSelectedItems().stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
        });*/

        gridPerson.addItemDoubleClickListener(personItemDoubleClickEvent -> {
            Notification.show(personItemDoubleClickEvent.getItem().getProfession());
        });

        gridPerson.setPartNameGenerator(person ->{
            if(person.getSalary()<80000){
                return "low-salary";
            }else{
                return "high-salary";
            }
        });


       /*  TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(event -> {
            dataView.refreshAll();
        });

        dataView.addFilter(person -> {
            String searchTerm  = searchField.getValue();

            if(searchTerm == null){
                return true;
            }

            boolean matchFirstName = matchesTerm(person.getFirstName(),searchTerm);
            boolean matchLastName = matchesTerm(person.getLastName(),searchTerm);
            boolean matchGender = matchesTerm(person.getGender(),searchTerm);

            return matchFirstName || matchLastName || matchGender;

        });*/

        PersonFilter personFilter = new PersonFilter(dataView);

        gridPerson.getHeaderRows().clear();
        headerRow = gridPerson.appendHeaderRow();

        headerRow.getCell(columnFirstName).setComponent(
                createFilterHeader("Name", personFilter::setFirstName));
        headerRow.getCell(columnGender).setComponent(
                createFilterHeader("Gender", personFilter::setGender));
        headerRow.getCell(columnSalary).setComponent(
                createFilterHeaderForSalary("Salary", personFilter::setSalary));

        add(gridPerson);

    }

    private boolean matchesTerm(String value, String searchTerm) {
        return value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    private static Renderer<Person> createPersonRenderer() {
        return LitRenderer.<Person> of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                                + "    <span> ${item.firstname}  ${item.lastname}</span>"
                                + "    <span style=\"font-size: var(--lumo-font-size-s); color: var(--lumo-secondary-text-color);\">"
                                + "      ${item.profession}" + "    </span>"
                                + "  </vaadin-vertical-layout>"
                                + "</vaadin-horizontal-layout>")
                .withProperty("firstname", Person::getFirstName)
                .withProperty("lastname", Person::getLastName)
                .withProperty("profession", Person::getProfession);
    }

    private static String createSalaryFooterText(List<Person> people) {
        double sumSalary = people.stream().mapToDouble(Person::getSalary).sum();

        return String.format("%s sum", sumSalary);
    }

    private static String createPersonCount(List<Person> people) {
        long totalRecords = people.stream().count();

        return String.format("%s total people", totalRecords);
    }

    private static Component createFilterHeader(String labelText,
                                                Consumer<String> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }

    private static Component createFilterHeaderForSalary(String labelText,
                                                Consumer<Integer> filterChangeConsumer) {
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(Integer.valueOf(e.getValue())));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }
}
