package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.List;


public interface AuthorService {

    void seedAuthors() throws IOException;

    int getAuthorsCount();

    Author findAuthorById(long id);

    List<Author> findAllAuthorsByBookCount();
}
