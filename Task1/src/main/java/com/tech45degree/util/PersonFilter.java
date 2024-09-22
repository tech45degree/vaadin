package com.tech45degree.util;

import com.tech45degree.model.Person;

public class PersonFilter {
    private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }


    public boolean filter(Person person) {
        boolean matchesFullName = matches(person.getFirstName(), searchTerm);
        boolean matchProfession = matches(person.getProfession(), searchTerm);
        boolean matchEmail = matches(person.getEmail(), searchTerm);

        return matchesFullName || matchProfession || matchEmail;
    }

    private boolean matches(String value, String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty()
                || value.toLowerCase().contains(searchTerm.toLowerCase());
    }
}
