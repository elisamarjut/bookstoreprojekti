package hh.sof03.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.sof03.bookstore.domain.Book;
import hh.sof03.bookstore.domain.BookRepository;
import hh.sof03.bookstore.domain.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("index")
    public String welcome() {
        return "store";
    }

    @RequestMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    // Add a new book
    @RequestMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    // Save new book
    // kun save saa käsiteltäväkseen kirjan ilman id:tä, tapahtuu create
    // ja kun saa kirjan id:llä, tapauhtuu update
    // jatkossa kannattaa ehkä jakaa useampaan metodiin mm. validointia varten
    // jos pitää samassa metodissa, tulee paljon ehdollisia haaroja
    @PostMapping("/savebook")
    public String saveBook(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }

    // Delete a book
    @GetMapping("/deletebook/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:../booklist";
    }

    // Edit book
    @RequestMapping("/editbook/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    // Login page
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}