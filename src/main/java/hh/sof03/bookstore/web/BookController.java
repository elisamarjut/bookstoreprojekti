package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @RequestMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Add new book
    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // Save new book
    @PostMapping("/save")
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    // Delete a book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

}