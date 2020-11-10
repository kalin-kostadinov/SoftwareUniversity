package com.softuni.springintroex.controllers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;


@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private static final Scanner sc = new Scanner(System.in);

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        System.out.println("Greetings fellow coder!");
        System.out.println("Before you start: ");
        System.out.println();
        System.out.println("\t!!! Change you password and username in application.properties !!!");
        System.out.println();
        outer:
        while (true) {
            System.out.println();
            System.out.println("For Exercise one press 1:");
            System.out.println("For Exercise two press 2:");
            System.out.println("For Exercise three press 3:");
            System.out.println("For Exercise four press 4:");
            System.out.println("For Exit press 0:");


            String input = sc.nextLine();


            switch (input) {

                case "0":
                    break outer;
                case "1":
                    this.bookService.getAllBooksAfter2000Ex1().forEach(b -> System.out.println(b.getTitle()));
                    break;

                case "2":
                    Set<Author> authorSet = new LinkedHashSet<>();
                    this.bookService.getAllBooksBefore1990().forEach(b -> authorSet.add(b.getAuthor()));
                    authorSet.forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
                    break;
                case "3":
                    this.authorService.findAllAuthorsByBookCount()
                            .forEach(a -> System.out.printf("%s %s has %d books.%n",
                                    a.getFirstName(), a.getLastName(),
                            a.getBooks().size()));
                    break;
                case "4":
                    this.bookService.getAllPowellBooks().forEach(b -> System.out.printf("%s was released %s with %d copies.%n",
                            b.getTitle(),
                            b.getReleaseDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            b.getCopies()));
                    break;
            }
        }

    }
}
