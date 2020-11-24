package com.product.domain.dtos.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldProductRootDto {

    @XmlElement(name = "product")
    private List<UserSoldProductDto> products;

    public UserSoldProductRootDto() {
    }

    public List<UserSoldProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserSoldProductDto> products) {
        this.products = products;
    }
}
