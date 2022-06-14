package spring.config.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.config.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year_of_publication) VALUES( ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET title = ?,  author = ?, year_of_publication = ? WHERE book_id = ?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYearOfPublication(), id);
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }

    public void takeBookFromReader(int id){
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE book_id = ?", id);
    }

    public void giveBookFromReader(int bookId, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?", personId, bookId);
    }

    public List<Book> showPersonsBookList(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id}, new BookMapper());
    }

}
