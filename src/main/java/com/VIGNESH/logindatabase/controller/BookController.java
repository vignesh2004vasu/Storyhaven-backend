package com.VIGNESH.logindatabase.controller;

//import com.VIGNESH.logindatabase.exception.BookNotFoundException;
import com.VIGNESH.logindatabase.exception.BookNotFoundException;
import com.VIGNESH.logindatabase.model.Book;
import com.VIGNESH.logindatabase.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;

@RestController
// @CrossOrigin("http://localhost:3000")
// @CrossOrigin("https://storyhaven.netlify.app/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    
    
    @PostMapping("/add")
    public List<Book> addBooks(@RequestBody List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/pagebooks")
    Page<Book> getAllBooks(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "4") int size,
                           @RequestParam(defaultValue = "title") String sortBy) {
        return bookRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }
    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/book/{id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable String id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setGenre(newBook.getGenre());
                    book.setPrice(newBook.getPrice());
                    book.setReviews(newBook.getReviews());
                    book.setImageUrl(newBook.getImageUrl());
                    return bookRepository.save(book);
                }).orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable String id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return "Book with ID " + id + " has been deleted successfully.";
    }


    @GetMapping("/books/sortedByAuthor")
    List<Book> getAllBooksSortedByAuthor() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "author"));
    }


    @GetMapping("/books/sortedByGenre")
    List<Book> getAllBooksSortedByGenre() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "genre"));
    }

    @GetMapping("/books/byAuthor/{author}")
    List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @GetMapping("/books/byGenre/{genre}")
    List<Book> getBooksByGenre(@PathVariable String genre) {
        return bookRepository.findByGenre(genre);
    }

    @GetMapping("/books/priceLessThan/{price}")
    List<Book> getBooksPriceLessThan(@PathVariable String price) {
        return bookRepository.findByPriceLessThan(price);
    }
}
