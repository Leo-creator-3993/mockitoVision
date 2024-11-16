package com.future.rocket.mockito.vision.service;

import com.future.rocket.mockito.vision.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private List<Person> personList;

    public PersonService() {
        personList = new ArrayList<>();
    }

    public void add(Person p) {
        personList.add(p);
    }

    public List<Person> findPerson(int age) {
        return personList.stream().filter(p -> p.getAge() > age).collect(Collectors.toList());
    }

    public Person findPerson(String name) {
        return personList.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
    }
}
