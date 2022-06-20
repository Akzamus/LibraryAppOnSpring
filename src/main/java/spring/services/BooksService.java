package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.models.Book;
import spring.models.Person;
import spring.repositories.BooksRepository;

import java.util.Date;
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

    public List<Book> index(int pageNumber, int booksPerPage, boolean sortIt){
        List<Book> books ;
        if(pageNumber >= 0 && booksPerPage > 0 && sortIt) {
            books = booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage,
                                            Sort.by("yearOfPublication")))
                                            .getContent();
        }else if(pageNumber >= 0 && booksPerPage > 0) {
            books = booksRepository.findAll(PageRequest.of(pageNumber, booksPerPage))
                                            .getContent();
        }else if(pageNumber <= 0 && booksPerPage <= 0 && sortIt){
            books = booksRepository.findAll(Sort.by("yearOfPublication"));
        }else{
            books = booksRepository.findAll();
        }
        return books;
    }

    public Book show(int id) {
        Optional<Book> fondBook = booksRepository.findById(id);
        return fondBook.orElse(null);
    }

    public List<Book> findBySearch(String searchQuery){
        return booksRepository.findByTitleContainingIgnoreCase(searchQuery);
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
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setReader(null);
            book.setGiveAt(null);
        });
    }

    @Transactional
    public void giveBookFromReader(int bookId, Person person) {
        booksRepository.findById(bookId).ifPresent(book -> {
            book.setReader(person);
            book.setGiveAt(new Date());
        });
    }
}
