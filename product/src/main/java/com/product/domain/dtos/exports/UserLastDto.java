package com.product.domain.dtos.exports;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserLastDto {

    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last_name")
    private String lastName;
    @XmlAttribute(name = "age")
    private int age;
    @XmlElement(name = "sold-products")
    private UserLastProductRootDto productsSold;

    public UserLastDto() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserLastProductRootDto getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(UserLastProductRootDto productsSold) {
        this.productsSold = productsSold;
    }
}
