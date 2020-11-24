package com.product.domain.repositories;

import com.product.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.productsSold.size > 0 order by u.lastName, u.firstName")
    List<User> usersSoldProducts();

    @Query("SELECT u FROM User u where u.productsSold.size > 0 ORDER BY u.productsSold.size DESC, u.lastName")
    List<User> usersLast();
}
