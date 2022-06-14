package spring.config.models;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    @NotEmpty(message = "Full name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your full name should be in this format: Alexander Sergeyevich Pushkin")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fullName;
    @Min(value = 1900, message = "Age should be greater than 1900")
    private int yearOfBirth;
    private List<Book> bookList = new ArrayList<>();

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.fullName = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
