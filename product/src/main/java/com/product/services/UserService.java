package com.product.services;

import javax.xml.bind.JAXBException;

public interface UserService {

    String seedUsers() throws JAXBException;

    String usersSoldProducts() throws JAXBException;

    String usersAndProducts() throws JAXBException;
}
