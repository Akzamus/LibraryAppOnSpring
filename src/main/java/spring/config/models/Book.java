package spring.config.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Author full name should be in this format: Alexander Sergeyevich Pushkin")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String author;
    @Min(value = 1500, message = "Year of publication should be greater than 1500")
    private int yearOfPublication;
    private int readerId;
    private String readerFullName;

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getReaderId() {
        return readerId;
    }

    public String getReaderFullName() {
        return readerFullName;
    }

    public void setReaderFullName(String readerFullName) {
        this.readerFullName = readerFullName;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}
