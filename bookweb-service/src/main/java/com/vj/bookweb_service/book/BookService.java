package com.vj.bookweb_service.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get a book by id (returns null if it does not exist)
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Get books with title
    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Add a new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
}