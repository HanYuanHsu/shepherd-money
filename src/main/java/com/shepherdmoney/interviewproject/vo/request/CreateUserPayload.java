package com.shepherdmoney.interviewproject.vo.request;

import lombok.Data;

@Data
public class CreateUserPayload {

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String n) {
        name = n;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
