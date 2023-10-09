package com.example.ead_frontend_android.Response;

public class UserResponse {

    private String id ;
    private String email ;
    private String password ;
    private String nic ;
    private String phone ;
    private String userType ;
    private boolean isActive ;

    public UserResponse(String id, String email, String password, String nic, String phone, String userType, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nic = nic;
        this.phone = phone;
        this.userType = userType;
        this.isActive = isActive;
    }

    public UserResponse() {
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
}
