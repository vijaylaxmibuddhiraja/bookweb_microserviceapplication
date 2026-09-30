package com.vj.loan_service.loan;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer bookId;        // only the id – the book itself lives in book-service

    private String borrowerName;

    private LocalDate loanDate;

    // Empty constructor
    public Loan() {
    }

    public Loan(Integer bookId, String borrowerName, LocalDate loanDate) {
        this.bookId = bookId;
        this.borrowerName = borrowerName;
        this.loanDate = loanDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
}