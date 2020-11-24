package com.product;

import com.product.services.CategoryService;
import com.product.services.ProductService;
import com.product.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
public class Runner implements CommandLineRunner {

    private final ConfigurableApplicationContext applicationContext;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Runner(ConfigurableApplicationContext applicationContext, UserService userService, CategoryService categoryService, ProductService productService) {
        this.applicationContext = applicationContext;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws JAXBException {

//        System.out.println(this.userService.seedUsers());
//        System.out.println(this.categoryService.seedCategories());
        //System.out.println(this.productService.seedProducts());

//        System.out.println(this.productService.productsInRange());
//        System.out.println(this.userService.usersSoldProducts());
//        System.out.println(this.categoryService.categoriesByProducts());
//        System.out.println(this.userService.usersAndProducts());


        
        this.applicationContext.close();
    }
}
