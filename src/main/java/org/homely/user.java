package org.homely;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Document(collection = "users")
public class user {
    @Id
    private String userId;
    private String username;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String password;
    private List<String> properties;

    public user(String userId, String username, String email, String fullName, String address, String phoneNumber, String password) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.properties = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addProperty(String newProperty) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(newProperty);
    }

    public List<String> getProperties() {
        return properties;
    }
}
