package spring.config.dao;

import org.springframework.jdbc.core.RowMapper;
import spring.config.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("book_id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setYearOfPublication(resultSet.getInt("year_of_publication"));
        book.setReaderId(resultSet.getInt("person_id"));
        return book;
    }
}
