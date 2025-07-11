package com.haridwar.university.btech;

import com.haridwar.university.btech.models.User;
import lombok.Data;

@Data
public class UserDtoResponse {
    private String name;
    private String email;

    public UserDtoResponse() {
    }
    public UserDtoResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
