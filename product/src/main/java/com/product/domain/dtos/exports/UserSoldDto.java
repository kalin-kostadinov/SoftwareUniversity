package com.product.domain.dtos.exports;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldDto {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last_name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private UserSoldProductRootDto productsSold;

    public UserSoldDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserSoldProductRootDto getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(UserSoldProductRootDto productsSold) {
        this.productsSold = productsSold;
    }
}
