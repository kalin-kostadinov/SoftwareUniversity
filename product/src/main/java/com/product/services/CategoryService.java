package com.product.services;

import javax.xml.bind.JAXBException;

public interface CategoryService {

    String seedCategories() throws JAXBException;

    String categoriesByProducts() throws JAXBException;
}
