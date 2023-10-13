package com.example.ead_frontend_android.Response;

import com.example.ead_frontend_android.model.User;

public class UserResponse {

    private String id;
    private String email;
    private String name;
    private String password;
    private String nic;
    private String phone;
    private String userType;
    private boolean isActive;

    public UserResponse() {
    }

    public UserResponse(String id, String email, String name, String password, String nic, String phone, String userType, boolean isActive) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.nic = nic;
        this.phone = phone;
        this.userType = userType;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Create a method to return a User object
    public User getUser() {
        // Create a User instance and populate it with the user data
        User user = new User();

        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setNic(nic);
        user.setPhone(phone);
        user.setActive(isActive);
        return user;
    }
}
