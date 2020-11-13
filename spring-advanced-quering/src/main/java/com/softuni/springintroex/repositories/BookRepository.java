package com.softuni.springintroex.repositories;


import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);
Set<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate first, LocalDate same);
Set<Book> findAllByReleaseDateBefore(LocalDate first);
Set<Book> findBookByTitleContaining(String containedString);
Set<Book> findAllByAuthorLastNameStartingWith(String lastNamePart);

@Query("select count(b) from Book b where length(b.title) > :length ")
int countBooksByTitleLength(int length);

Book findBookByTitle(String title);



}
