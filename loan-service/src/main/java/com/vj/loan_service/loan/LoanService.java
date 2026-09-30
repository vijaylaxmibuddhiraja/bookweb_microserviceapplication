package com.vj.loan_service.loan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    // The book data we get back from book-service (same field names as its JSON)
    record BookInfo(Integer id, String title, String author, String category, String status) {
    }

    private final LoanRepository loanRepository;
    private final RestClient restClient;

    public LoanService(LoanRepository loanRepository,
                       @Value("${book.service.url}") String bookServiceUrl) {
        this.loanRepository = loanRepository;
        this.restClient = RestClient.builder().baseUrl(bookServiceUrl).build();
    }

    // Get all loans
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Borrow a book: ask book-service first, then save the loan
    public Loan createLoan(Integer bookId, String borrowerName) {
        BookInfo book;
        try {
            // 1. Synchronous call: GET the book from book-service and wait for the answer
            book = restClient.get()
                    .uri("/api/books/{id}", bookId)
                    .retrieve()
                    .body(BookInfo.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Book not found");
        } catch (ResourceAccessException e) {
            throw new RuntimeException("Book service is not reachable");
        }

        // 2. Check that the book is available
        if (!"Available".equalsIgnoreCase(book.status())) {
            throw new RuntimeException("Book is not available");
        }

        // 3. Synchronous call: tell book-service to mark the book as borrowed
        restClient.put()
                .uri("/api/books/{id}/borrow", bookId)
                .retrieve()
                .toBodilessEntity();

        // 4. Save the loan in our own database
        Loan loan = new Loan(bookId, borrowerName, LocalDate.now());
        return loanRepository.save(loan);
    }
}