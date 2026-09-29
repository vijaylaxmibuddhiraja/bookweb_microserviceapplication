package com.vj.bookweb_service.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/books – all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET /api/books/1 – book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();   // 404 Not Found
        }
        return ResponseEntity.ok(book);                  // 200 OK
    }

    // GET /api/books/title - books with title
    @GetMapping("/title")
    public List<Book> getBooksByTitle(@RequestParam String title) {
        return bookService.getBooksByTitle(title);
    }

    // POST /api/books – add a new book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}