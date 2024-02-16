package ru.alishov.springproject.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person
{
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 100, message = "Full Name should be between 5 and 100 characters")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your Full Name should be int this format: Father name, Name, Surname")
    private String fullName;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    public Person() {}

    public Person(String fullName, int age)
    {
        this.fullName = fullName;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
