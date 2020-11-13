package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.models.BookInfo;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    void bookTitlesByAgeRestriction(String ageRestriction);

    void goldenBooks();

    void booksByPrice();

    void notReleasedBooks(String year);

    void booksReleasedBeforeDate(String date);

    void bookSearch(String containingString);

    void bookTitleSearch(String authorLastNameBegining);

    void countBooks(int titleLength);

    BookInfo reducedBook(String title);
}
