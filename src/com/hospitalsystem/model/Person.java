package com.hospitalsystem.model;

/**
 * Member 3 - Abstract Person Base
 */
public abstract class Person {

    protected final String id;
    protected final String name;
    protected final int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract String getRole();

    public String getDetails() {
        return getRole() + ": " + name + " (ID: " + id + ", Age: " + age + ")";
    }
}
