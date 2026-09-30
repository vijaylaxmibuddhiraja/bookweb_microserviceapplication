package com.vj.bookweb_service.book;

import jakarta.persistence.*;

    @Entity
    @Table(name = "books")
    public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String title;

        private String author;

        private String category;

        private String status;

        public Book() {
        }

        // Constructor for creating new books
        public Book(String title, String author, String category, String status) {
            this.title = title;
            this.author = author;
            this.category = category;
            this.status = status;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public boolean isAvailable() {
            return "available".equals(status);
        }

        public void setAvailable(boolean available) {
            this.status = available ? "available" : "borrowed";
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", status='" + status + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }
    }

