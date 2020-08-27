package com.github.gwtboot.sample.ui.dncomponents.client.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dncomponents
 */
public class Data {
    public static ArrayList<Person> people = TestingHelper.getPeople(1000);

    public static void setNulls(List<Person> people) {
        people.get(3).setName(null);
        people.get(3).setActive(null);
        people.get(3).setCurrentColor(null);
        people.get(3).setAge(null);
        //
        people.get(4).setName(null);
        people.get(4).setActive(null);
        people.get(4).setCurrentColor(null);
        people.get(4).setAge(null);
    }


    public static ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();
        int b = 0;
        for (int i = 0; i < 50; i++) {
            for (Person person : TestingHelper.getPeople(1000)) {
                Person t = new Person();
                t.setId(b++);
                t.setName(person.getName());
                t.setActive(person.isActive());
                t.setCurrentColor(person.getCurrentColor());
                t.setAge(person.getAge());
                t.setDate(person.getDate());
                people.add(t);
            }
        }
        return people;
    }

}
