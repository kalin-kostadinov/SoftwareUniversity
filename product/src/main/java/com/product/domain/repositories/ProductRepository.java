package com.product.domain.repositories;

import com.product.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select p from Product p where p.price > 500 AND p.price < 1000 and p.buyer is null order by p.price")
    List<Product> productsInRange();
}
