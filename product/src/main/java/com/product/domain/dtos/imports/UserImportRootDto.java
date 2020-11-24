package com.product.domain.dtos.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportRootDto {

    @XmlElement(name = "user")
    private List<UserImportDto> users;

    public UserImportRootDto() {
    }

    public List<UserImportDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserImportDto> users) {
        this.users = users;
    }
}
