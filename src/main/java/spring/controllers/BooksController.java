package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.models.Book;
import spring.models.Person;
import spring.services.BooksService;
import spring.services.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model,
                        @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(name = "books_per_page", required = false, defaultValue = "0") int booksPerPage,
                        @RequestParam(name = "sort_by_year", required = false, defaultValue = "false") boolean sortIt){
        model.addAttribute("books", booksService.index(pageNumber, booksPerPage, sortIt));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model, @ModelAttribute("person") Person person){
        Book book =  booksService.show(id);
        if(book == null)
            return "redirect:/books";
        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String take(@PathVariable("id") int id) {
        booksService.takeBookFromReader(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/give")
    public String give(@PathVariable("id") int bookId, @ModelAttribute("person") Person person){
        booksService.giveBookFromReader( bookId, person);
        return "redirect:/books/{id}";
    }
}
