package com.product.services;

import com.product.domain.dtos.exports.UserLastDto;
import com.product.domain.dtos.exports.UserLastRootDto;
import com.product.domain.dtos.exports.UserSoldDto;
import com.product.domain.dtos.exports.UserSoldRootDto;
import com.product.domain.dtos.imports.UserImportDto;
import com.product.domain.dtos.imports.UserImportRootDto;
import com.product.domain.entities.User;
import com.product.domain.repositories.UserRepository;
import com.product.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final static String USERS_IMPORT = "src/main/resources/xml/import/users.xml";
    private final static String USERS_SOLD_PRODUCTS = "src/main/resources/xml/export/users-sold-products.xml";
    private final static String USERS_AND_PRODUCTS = "src/main/resources/xml/export/users-and-products.xml";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public String seedUsers() throws JAXBException {

        UserImportRootDto rootDto = this.xmlParser.parseXml(UserImportRootDto.class, USERS_IMPORT);

        for (UserImportDto user : rootDto.getUsers()) {
                this.userRepository.saveAndFlush(this.modelMapper.map(user, User.class));
        }
        return "Successfully seeded Users!";
    }

    @Override
    public String usersSoldProducts() throws JAXBException {

        List<User> users = this.userRepository.usersSoldProducts();

        UserSoldRootDto rootDto = new UserSoldRootDto();

        List<UserSoldDto> userSoldDtoList = users.stream()
                .map(u -> this.modelMapper.map(u, UserSoldDto.class)).collect(Collectors.toList());

        rootDto.setUsers(userSoldDtoList);

        this.xmlParser.exportXml(rootDto, UserSoldRootDto.class, USERS_SOLD_PRODUCTS);

        return "Successfully exported users. Check resources/export directory";
    }

    @Override
    public String usersAndProducts() throws JAXBException {

        List<User> users = this.userRepository.usersLast();

        UserLastRootDto root = new UserLastRootDto();
        List<UserLastDto> dtos = new ArrayList<>();

        for (User user : users) {
            UserLastDto userLastDto = this.modelMapper.map(user, UserLastDto.class);
            userLastDto.getProductsSold().setCount(user.getProductsSold().size());
            dtos.add(userLastDto);
        }

        root.setUsers(dtos);
        root.setCount(users.size());

        this.xmlParser.exportXml(root, UserLastRootDto.class, USERS_AND_PRODUCTS);


        return "Successfully exported users. Check resources/export directory";
    }
}
