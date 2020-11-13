package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


import static com.softuni.springintroex.constants.GlobalConstants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;

    }


    @Override
    public void seedAuthors() throws IOException {

        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            String[] tokens = r.split("\\s+");
            String firstName = tokens[0];
            String lastName = tokens[1];
            Author author = new Author(firstName, lastName);
            authorRepository.saveAndFlush(author);
        });

    }

    @Override
    public int getAuthorsCount() {

        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public void authorsSearch(String endOfName) {
        this.authorRepository.findAllByFirstNameEndsWith(endOfName).forEach(
                a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName())
        );
    }

    @Override
    public void totalBookCopies() {
        List<Author> authors = this.authorRepository.findAll();

        TreeMap<String, Integer> map = new TreeMap<>();

        for (Author author : authors) {
            String firstName = author.getFirstName();
            String lastName = author.getLastName();
            Set<Book> books = author.getBooks();

            String fullName = String.format("%s %s", firstName, lastName);
            int copies = books.stream().mapToInt(Book::getCopies).sum();

            map.put(fullName, copies);

        }

        map.entrySet().stream().sorted((c1, c2) ->
            Integer.compare(c2.getValue(), c1.getValue()))
                .forEach(e -> System.out.printf("%s - %d%n", e.getKey(), e.getValue()));
    }


}
