package spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
