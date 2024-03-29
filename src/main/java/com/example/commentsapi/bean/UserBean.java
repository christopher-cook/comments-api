package com.example.commentsapi.bean;

/**
 * @author Christopher Cook
 * @author Qin Hzu
 */
public class UserBean {

    private Long id;

    private String email;

    private String username;

    private String password;

    public UserBean(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * @param id takes a Long id as input
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
