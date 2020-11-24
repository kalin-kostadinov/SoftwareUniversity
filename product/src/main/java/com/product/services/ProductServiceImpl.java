package com.product.services;

import com.product.domain.dtos.exports.ProductExportDto;
import com.product.domain.dtos.exports.ProductExportRootDto;
import com.product.domain.dtos.imports.ProductImportDto;
import com.product.domain.dtos.imports.ProductImportRootDto;
import com.product.domain.entities.Category;
import com.product.domain.entities.Product;
import com.product.domain.entities.User;
import com.product.domain.repositories.CategoryRepository;
import com.product.domain.repositories.ProductRepository;
import com.product.domain.repositories.UserRepository;
import com.product.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final static String PRODUCT_IMPORT = "src/main/resources/xml/import/products.xml";
    private final static String PRODUCTS_IN_RANGE = "src/main/resources/xml/export/products-in-range.xml";

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public String seedProducts() throws JAXBException {

        ProductImportRootDto rootDto = this.xmlParser.parseXml(ProductImportRootDto.class, PRODUCT_IMPORT);

        for (ProductImportDto productDto : rootDto.getProducts()) {
            Product product = this.modelMapper.map(productDto, Product.class);
            product.setSeller(getRandomUser());
            product.setCategories(getRandomCategories(this.categoryRepository.findAll()));

            if (!(this.productRepository.count() % 5 == 0)) {
                product.setBuyer(getRandomUser());
            }
            this.productRepository.saveAndFlush(product);
        }

        return "Successfully seeded products!";
    }

    @Override
    public String productsInRange() throws JAXBException {

        List<Product> products = this.productRepository.productsInRange();

        ProductExportRootDto rootDto = new ProductExportRootDto();
        rootDto.setProducts(new ArrayList<>());

        for (Product product : products) {
            ProductExportDto dto = this.modelMapper.map(product, ProductExportDto.class);

            dto.setName(String.format("%s %s", product.getSeller().getFirstName()
                    , product.getSeller().getLastName()));

            rootDto.getProducts().add(dto);
        }
        this.xmlParser.exportXml(rootDto, ProductExportRootDto.class, PRODUCTS_IN_RANGE);

        return "Successfully exported products. Check resources/xml/export directory";
    }

    private User getRandomUser() {
        Random random = new Random();

        int index = random.nextInt((int) this.userRepository.count()) + 1;

        return this.userRepository.getOne((long) index);
    }

    private Set<Category> getRandomCategories(List<Category> categories) {
        Random random = new Random();
        int id = random.nextInt(categories.size()) - 1;
        if (id == -1) {
            id = 0;
        }
        if (id == categories.size()) {
            id = categories.size() - 1;
        }
        Set<Category> categoriesSet = new HashSet<>();
        categoriesSet.add(categories.get(id));
        id = random.nextInt(categories.size() - 1);
        if (id == -1) {
            id = 0;
        }
        if (id == categories.size()) {
            id = categories.size() - 1;
        }
        categoriesSet.add(categories.get(id));
        return categoriesSet;
    }
}
