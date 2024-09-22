package com.tech45degree.util;

import com.tech45degree.model.Person;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

public class PersonFilter {
    private final GridListDataView<Person> dataView;

    private String firstName;
    private String gender;
    private int salary;

    public PersonFilter(GridListDataView<Person> dataView) {
        this.dataView = dataView;
        this.dataView.addFilter(this::filter);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.dataView.refreshAll();
    }

    public void setGender(String gender) {
        this.gender = gender;
        this.dataView.refreshAll();
    }

    public void setSalary(int salary) {
        this.salary = salary;
        this.dataView.refreshAll();
    }

    public boolean filter(Person person) {
        boolean matchesFullName = matches(person.getFirstName(), firstName);
        boolean matchGender = matches(person.getGender(), gender);
        boolean matchSalary = matchesSalary(person.getSalary(),
                salary);

        return matchesFullName && matchGender && matchSalary;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }

    private boolean matchesSalary(int value, int searchTerm) {
        return searchTerm == 0 || value >= searchTerm;
    }
}
