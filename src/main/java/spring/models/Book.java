package spring.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Column(name = "title")
    private String title;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Author full name should be in this format: Alexander Sergeyevich Pushkin")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 1500, message = "Year of publication should be greater than 1500")
    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @Column(name = "give_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giveAt;

    @Transient
    private boolean isOverdue;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person reader;

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

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public Date getGiveAt() {
        return giveAt;
    }

    public void setGiveAt(Date giveAt) {
        this.giveAt = giveAt;
    }

    public boolean isOverdue() {
        Date date = getGiveAt();
        if(date == null) return false;
        if(new Date().getTime() - date.getTime() >= 864000000L)  return true;
        else return false;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }
}
