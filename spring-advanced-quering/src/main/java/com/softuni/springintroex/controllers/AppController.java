package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;


@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private static final Scanner scanner = new Scanner(System.in);

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

        System.out.println();
        System.out.println("Greetings fellow coder! Before you start: ");
        System.out.println();
        System.out.println("\t!!! Change you password and username in application.properties !!!");

        outer:
        while (true) {
            System.out.println();
            System.out.println("For Exercise one press 1:");
            System.out.println("For Exercise two press 2:");
            System.out.println("For Exercise three press 3:");
            System.out.println("For Exercise four press 4:");
            System.out.println("For Exercise five press 5:");
            System.out.println("For Exercise six press 6:");
            System.out.println("For Exercise seven press 7:");
            System.out.println("For Exercise eight press 8:");
            System.out.println("For Exercise nine press 9:");
            System.out.println("For Exercise ten press 10:");
            System.out.println("For Exercise eleven press 11:");
            System.out.println("For Exit press 0:");


            String input = scanner.nextLine();


            switch (input) {

                case "0":
                    break outer;
                case "1":
                    System.out.println("Exercise 1 initiated...");
                    System.out.println("Enter age restriction:");
                    String ageInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.bookService.bookTitlesByAgeRestriction(ageInput);
                    break;

                case "2":
                    System.out.println("Exercise 2 initiated...");
                    System.out.println("Output:");
                    this.bookService.goldenBooks();
                    break;
                case "3":
                    System.out.println("Exercise 3 initiated...");
                    System.out.println("Output:");
                    this.bookService.booksByPrice();
                    break;
                case "4":
                    System.out.println("Exercise 4 initiated...");
                    System.out.println("Enter release year:");
                    String yearInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.bookService.notReleasedBooks(yearInput);
                    break;
                case "5":
                    System.out.println("Exercise 5 initiated...");
                    System.out.println("Enter date in format dd-MM-yyyy :");
                    String dateInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.bookService.booksReleasedBeforeDate(dateInput);
                    break;
                case "6":
                    System.out.println("Exercise 6 initiated...");
                    System.out.println("Enter the last part of author`s first name:");
                    String nameInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.authorService.authorsSearch(nameInput);
                    break;
                case "7":
                    System.out.println("Exercise 7 initiated...");
                    System.out.println("Enter the containing string:");
                    String stringInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.bookService.bookSearch(stringInput);
                    break;
                case "8":
                    System.out.println("Exercise 8 initiated...");
                    System.out.println("Enter the part of author`s name:");
                    String authorNamePartInput = scanner.nextLine();
                    System.out.println("Output:");
                    System.out.println();
                    this.bookService.bookTitleSearch(authorNamePartInput);
                    break;
                case "9":
                    System.out.println("Exercise 9 initiated...");
                    System.out.println("Enter title length minimum:");
                    int titleLength = Integer.parseInt(scanner.nextLine());
                    System.out.println("Number of books with longer title:");
                    this.bookService.countBooks(titleLength);
                    break;
                case "10":
                    System.out.println("Exercise 10 initiated...");
                    System.out.println("Output:");
                    this.authorService.totalBookCopies();
                    break;
                case "11":
                    System.out.println("Exercise 11 initiated...");
                    System.out.println("Enter book title :");
                    String title = scanner.nextLine();
                    System.out.println("Output:");
                    BookInfo bookInfo = this.bookService.reducedBook(title);
                    System.out.printf("%s %s %s %s%n", bookInfo.getTitle(),
                            bookInfo.getEditionType(), bookInfo.getAgeRestriction(),
                            bookInfo.getPrice());
                    break;
                default:
                    System.out.println("No such exercise! Try again!");
            }
        }

    }
}
