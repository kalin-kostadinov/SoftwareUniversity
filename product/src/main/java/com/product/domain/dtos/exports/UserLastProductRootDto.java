package com.product.domain.dtos.exports;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserLastProductRootDto {

    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "product")
    private List<UserLastProductDto> products;

    public UserLastProductRootDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserLastProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<UserLastProductDto> products) {
        this.products = products;
    }
}
