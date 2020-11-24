package com.product.services;

import javax.xml.bind.JAXBException;

public interface ProductService {

    String seedProducts() throws JAXBException;

    String productsInRange() throws JAXBException;
}
