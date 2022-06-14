package spring.config.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.config.dao.BookDAO;
import spring.config.dao.PersonDAO;
import spring.config.models.Book;
import spring.config.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model, @ModelAttribute("person")Person person){
        Book book =  bookDAO.show(id);
        if(book == null)
            return "redirect:/books";
        int readerId = book.getReaderId();
        if(readerId != 0)
            book.setReaderFullName(personDAO.show(readerId).getFullName());
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String take(@PathVariable("id") int id) {
        System.out.println(id);
        bookDAO.takeBookFromReader(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/give")
    public String give(@PathVariable("id") int bookId, @ModelAttribute("person") Person person){
        bookDAO.giveBookFromReader( bookId, person.getId());
        return "redirect:/books/{id}";
    }
}
