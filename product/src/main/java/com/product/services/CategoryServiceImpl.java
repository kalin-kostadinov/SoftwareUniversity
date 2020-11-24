package com.product.services;

import com.product.domain.dtos.exports.CategoryExportDto;
import com.product.domain.dtos.exports.CategoryExportRootDto;
import com.product.domain.dtos.imports.CategoryImportDto;
import com.product.domain.dtos.imports.CategoryImportRootDto;
import com.product.domain.entities.Category;
import com.product.domain.repositories.CategoryRepository;
import com.product.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORY_IMPORT = "src/main/resources/xml/import/categories.xml";
    private final static String CATEGORIES_BY_PRODUCTS = "src/main/resources/xml/export/categories-by-products.xml";


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    @Override
    public String seedCategories() throws JAXBException {
        CategoryImportRootDto rootDto = this.xmlParser.parseXml(CategoryImportRootDto.class, CATEGORY_IMPORT);

        for (CategoryImportDto dto : rootDto.getCategories()) {
            this.categoryRepository.saveAndFlush(this.modelMapper.map(dto, Category.class));
        }
        return "Successfully seeded categories!";
    }

    @Override
    public String categoriesByProducts() throws JAXBException {

        List<Category> categories = this.categoryRepository.categoriesByProducts();

        CategoryExportRootDto rootDto = new CategoryExportRootDto();
        rootDto.setCategories(new ArrayList<>());

        for (Category category : categories) {
            CategoryExportDto dto = new CategoryExportDto();
            dto.setName(category.getName());
            dto.setProductsCount(category.getProducts().size());
            dto.setAveragePrice(category.getProducts()
                    .stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .average()
                    .orElse(0.0));
            dto.setTotalRevenue(category.getProducts()
                    .stream()
                    .mapToDouble(p -> p.getPrice().doubleValue())
                    .sum());
            rootDto.getCategories().add(dto);
        }


        this.xmlParser.exportXml(rootDto, CategoryExportRootDto.class, CATEGORIES_BY_PRODUCTS);

        return "Successfully exported categories. Check resources/export directory";
    }
}
