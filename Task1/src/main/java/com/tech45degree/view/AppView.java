package com.tech45degree.view;

import com.tech45degree.model.Person;
import com.tech45degree.util.DataGenerator;
import com.tech45degree.util.PersonDataProvider;
import com.tech45degree.util.PersonFilter;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("app")
public class AppView extends VerticalLayout {

    public AppView() {
        setSizeFull();

        createHeader();
        createContent();
        createFooter();
    }

    private void createHeader() {
        VerticalLayout header = new VerticalLayout();

        MenuBar headerMenu = new MenuBar();

        headerMenu.addClassName("header-menu");

        headerMenu.addItem("Home");
        headerMenu.addItem("Portfolio");
        headerMenu.addItem("Services");
        headerMenu.addItem("About Us");
        headerMenu.addItem("Contact Us");

        header.add(headerMenu);
        header.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(header);
    }

    private void createContent() {
        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();

        content.add(createLeftContent());

        Component gridLayout = createCenterContent();
        Component formLayout =  createRightContent();

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.addToPrimary(gridLayout);
        splitLayout.addToSecondary(formLayout);
        splitLayout.setSplitterPosition(70);

        content.add(splitLayout);

        add(content);
    }

    private VerticalLayout createLeftContent() {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("200px");

        Accordion accordionLinks  = new Accordion();
        accordionLinks.addClassName("link-accordion");

        AccordionPanel panelServices = new AccordionPanel();
        panelServices.setSummaryText("Services");
        panelServices.add(createContent(createStyledAnchor("#","Consultation"),
                createStyledAnchor("#","Product Development"),
                createStyledAnchor("#","Cloud Services")));

        AccordionPanel panelAbout= new AccordionPanel();
        panelAbout.setSummaryText("About");
        panelAbout.add(createContent(createStyledAnchor("#","About Us"),
                createStyledAnchor("#","Contact Us")));

        AccordionPanel panelPortfolio = new AccordionPanel();
        panelPortfolio.setSummaryText("Portfolio");
        panelPortfolio.add(createContent(createStyledAnchor("#","Content Management System"),
                createStyledAnchor("#","Document Management System"),
                createStyledAnchor("#","Learning Management System")));

        accordionLinks.add(panelServices);
        accordionLinks.add(panelAbout);
        accordionLinks.add(panelPortfolio);

        accordionLinks.open(panelAbout);

        verticalLayout.add(accordionLinks);
        return verticalLayout;
    }

    private VerticalLayout createCenterContent() {
    VerticalLayout verticalLayout = new VerticalLayout();

        PersonFilter personFilter = new PersonFilter();

        PersonDataProvider personDataProvider = new PersonDataProvider();

        ConfigurableFilterDataProvider<Person,Void,PersonFilter> configurableFilterDataProvider = personDataProvider.withConfigurableFilter();

        Grid<Person> gridPerson = new Grid<>(Person.class,false);

        gridPerson.addClassName("person-grid");

        gridPerson.setItems(configurableFilterDataProvider);
        gridPerson.setPageSize(20);
        gridPerson.setSelectionMode(Grid.SelectionMode.MULTI);

        gridPerson.addColumn(createPersonRenderer())
                .setHeader("Person")
                .setResizable(true);

        gridPerson.addColumn(Person::getAge,"age")
                .setHeader("Age")
                .setSortable(true)
                .setSortProperty("age");

        gridPerson.addColumn(new ComponentRenderer<>(person -> {
                    if (person.getGender().equalsIgnoreCase("Male")) {
                        Icon iconMale = VaadinIcon.MALE.create();
                        iconMale.setColor("darkslateblue");
                        return iconMale;
                    } else {
                        Icon iconFemale = VaadinIcon.FEMALE.create();
                        iconFemale.setColor("deeppink");
                        return iconFemale;
                    }
                })).setHeader("Gender")
                .setComparator(Person::getGender)
                .setSortProperty("gender")
                .setWidth("5px")
                .setTooltipGenerator(person -> person.getGender());

        gridPerson.addColumn(Person::getSalary)
                .setHeader("Salary ($)").setSortable(true)
                .setTextAlign(ColumnTextAlign.END)
                .setWidth("20px");

        gridPerson.addColumn(Person::getProfession,"profession")
                .setHeader("Profession")
                .setSortable(true)
                .setResizable(true);

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


        verticalLayout.add(searchField,gridPerson);


    return verticalLayout;
    }

    private FormLayout createRightContent() {
        FormLayout formLayout = new FormLayout();
        formLayout.addClassName("form-layout-person");

        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        NumberField age = new NumberField("Age");
        age.addClassName("number-age");
        age.setValue(25.0);
        age.setStep(1);
        age.setMax(65);
        age.setMin(0);
        age.setStepButtonsVisible(true);

        EmailField email = new EmailField("Email");

        ComboBox<String> gender = new ComboBox<>();
        gender.setLabel("Gender");
        gender.setItems(DataGenerator.getGender());

        NumberField salary = new NumberField("Salary");
        Icon icoDollar = VaadinIcon.DOLLAR.create();
        icoDollar.setColor("goldenrod");
        salary.setSuffixComponent(icoDollar);

        TextField profession = new TextField("Profession");

        Button btnReset = new Button("Reset");
        btnReset.addThemeVariants(ButtonVariant.LUMO_ERROR,ButtonVariant.LUMO_PRIMARY);

        Button btnSave = new Button("Save");
        btnSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY);

        formLayout.add(firstName, lastName, age, email, gender, salary, profession,btnReset,btnSave);

        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 2));

        // Stretch the  field over 2 columns
        formLayout.setColspan(email, 2);
        formLayout.setColspan(profession, 2);

        return formLayout;
    }

    private void createFooter() {
        VerticalLayout footer = new VerticalLayout();

        H6 lblFooter = new H6("© 20** Tech45Degree. All Rights Reserved. Empowering Learners with Cutting-Edge Tech Skills. Follow us: [Facebook] | [Twitter] | [LinkedIn] | [Instagram] | Need help? Contact us at info@tech45degree.com");
        lblFooter.getStyle().setColor("orange");
        lblFooter.getStyle().setFontWeight("bolder");
        footer.add(lblFooter);
        footer.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(footer);
    }

    private VerticalLayout createContent(Anchor... anchors) {
        VerticalLayout content = new VerticalLayout();
        content.setPadding(false);
        content.setSpacing(false);
        content.add(anchors);

        return content;
    }

    private Anchor createStyledAnchor(String href, String text) {
        Anchor anchor = new Anchor(href, text);
        anchor.getStyle().set("color", "darkslategray");
        anchor.getStyle().set("text-decoration", "none");

        return anchor;
    }

    private static Renderer<Person> createPersonRenderer() {
        return LitRenderer.<Person> of(
                        "<vaadin-horizontal-layout style=\"align-items: center;\" theme=\"spacing\">"
                                + "  <vaadin-avatar img=\"${item.pictureUrl}\" name=\"${item.firstName}\"></vaadin-avatar>"
                                + "  <vaadin-vertical-layout style=\"line-height: var(--lumo-line-height-m);\">"
                                + "    <span> ${item.firstName} ${item.lastName}</span>"
                                + "    <span style=\"font-size: var(--lumo-font-size-s); color: var(--lumo-secondary-text-color);\">"
                                + "      ${item.email}" + "    </span>"
                                + "  </vaadin-vertical-layout>"
                                + "</vaadin-horizontal-layout>")
                .withProperty("pictureUrl", Person::getPictureUrl)
                .withProperty("firstName", Person::getFirstName)
                .withProperty("lastName", Person::getLastName)
                .withProperty("email", Person::getEmail);
    }
}
