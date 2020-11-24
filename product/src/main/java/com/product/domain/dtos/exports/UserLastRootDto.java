package com.product.domain.dtos.exports;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserLastRootDto {

    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "user")
    private List<UserLastDto> users;

    public UserLastRootDto() {
    }

    public List<UserLastDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserLastDto> users) {
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}