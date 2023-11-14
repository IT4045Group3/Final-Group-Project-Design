package com.groupthree.culinarycompanion.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class RememberMeToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rememberMeTokenId;
    private int userId;
    private String token;
    private Date expirationDate;

    public int getRememberMeTokenId() {
        return rememberMeTokenId;
    }

    public void setRememberMeTokenId(int rememberMeTokenId) {
        this.rememberMeTokenId = rememberMeTokenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
