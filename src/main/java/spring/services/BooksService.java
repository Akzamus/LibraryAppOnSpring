package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.models.Book;
import spring.models.Person;
import spring.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index(){
        return booksRepository.findAll();
    }

    public Book show(int id) {
        Optional<Book> fondBook = booksRepository.findById(id);
        return fondBook.orElse(null);
    }

    @Transactional
    public void save(Book newBook) {
        booksRepository.save(newBook);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void  takeBookFromReader(int bookId) {
        booksRepository.findById(bookId).ifPresent(book -> book.setReader(null));
    }

    @Transactional
    public void giveBookFromReader(int bookId, Person person) {
        booksRepository.findById(bookId).ifPresent(book -> book.setReader(person));
    }
}
