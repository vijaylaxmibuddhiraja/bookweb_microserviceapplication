package com.vj.loan_service.loan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final com.vj.loan_service.loan.LoanService loanService;

    public LoanController(com.vj.loan_service.loan.LoanService loanService) {
        this.loanService = loanService;
    }

    // GET /api/loans – all loans
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    // POST /api/loans?bookId=1&borrowerName=Anna – borrow a book
    @PostMapping
    public ResponseEntity<?> createLoan(@RequestParam Integer bookId, @RequestParam String borrowerName) {
        try {
            return ResponseEntity.ok(loanService.createLoan(bookId, borrowerName));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());   // 400 with the error message
        }
    }
}