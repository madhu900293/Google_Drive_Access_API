package com.haridwar.university.btech.UerDtoResponse;

import com.haridwar.university.btech.models.User;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;



    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    public UserDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
