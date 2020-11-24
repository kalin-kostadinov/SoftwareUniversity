package com.product.domain.dtos.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldRootDto {

    @XmlElement(name = "user")
    private List<UserSoldDto> users;

    public UserSoldRootDto() {
    }

    public List<UserSoldDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldDto> users) {
        this.users = users;
    }
}
