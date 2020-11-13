package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Set<Author>findAllByFirstNameEndsWith(String endOfName);

//    @Query("select a from Author a JOIN Book b order by b.copies")
//    List<Author> findAuthorByBookCopies();



}
