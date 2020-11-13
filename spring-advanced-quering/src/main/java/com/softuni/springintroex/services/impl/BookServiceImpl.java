package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.BOOKS_FILE_PATH;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;

        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            String[] tokens = r.split("\\s+");

            Author author = getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(tokens[1], dateTimeFormatter);
            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
            StringBuilder builder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                builder.append(tokens[i]).append(" ");
            }
            String title = builder.toString().trim();

            Set<Category> categories = getRandomCategories();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);


        });

    }

    @Override
    public void bookTitlesByAgeRestriction(String ageRestriction) {
        Set<Book> books = this.bookRepository.
                findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void goldenBooks() {
        Set<Book> books = this.bookRepository.findAllByEditionTypeAndCopiesLessThan(
                EditionType.GOLD, 5000);
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void booksByPrice() {
        Set<Book> books = this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(
                BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        System.out.println();
        books.forEach(
                b -> System.out.printf("%s - $%s%n", b.getTitle(), b.getPrice())
        );
    }

    @Override
    public void notReleasedBooks(String year) {
        int inputYear = Integer.parseInt(year);
        LocalDate localDate = LocalDate.of(inputYear, 1, 1);
        this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(localDate, localDate).forEach(
                b -> System.out.println(b.getTitle())
        );


    }

    @Override
    public void booksReleasedBeforeDate(String date) {
        this.bookRepository.findAllByReleaseDateBefore
                (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))).forEach(
                b -> System.out.printf("%s %s %s%n", b.getTitle(), b.getEditionType()
                        , b.getPrice())
        );
    }

    @Override
    public void bookSearch(String containingString) {
        this.bookRepository.findBookByTitleContaining(containingString).forEach(
                b -> System.out.println(b.getTitle())
        );
    }

    @Override
    public void bookTitleSearch(String authorLastNameBegining) {
        this.bookRepository.findAllByAuthorLastNameStartingWith(authorLastNameBegining).forEach(
                b -> System.out.printf("%s (%s %s)&n", b.getTitle(),
                        b.getAuthor().getFirstName(), b.getAuthor().getLastName())
        );
    }

    @Override
    public void countBooks(int titleLength) {
        System.out.println(this.bookRepository.countBooksByTitleLength(titleLength));
    }

    @Override
    public BookInfo reducedBook(String title) {
        Book book = this.bookRepository.findBookByTitle(title);

        String infoTitle = book.getTitle();
        String infoEdition = book.getEditionType().name();
        String infoAge = book.getAgeRestriction().name();
        BigDecimal price = book.getPrice();

        return new BookInfo(infoTitle, infoEdition, infoAge, price);
    }


    private Set<Category> getRandomCategories() {

        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;


        for (int i = 1; i <= bound; i++) {
            int id = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById(id));
        }


        return result;
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int nextInt = random.nextInt(this.authorService.getAuthorsCount()) + 1;
        return this.authorService.findAuthorById(nextInt);
    }
}
