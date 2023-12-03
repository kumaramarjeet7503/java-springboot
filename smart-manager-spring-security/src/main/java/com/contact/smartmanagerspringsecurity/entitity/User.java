package com.contact.smartmanagerspringsecurity.entitity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List ;
import java.util.ArrayList ;

@Entity 
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private   int id ;
    private String name ;
    @Column(unique = true)
    private String email ;
    private String password ;
    private String about ;
    private boolean enabled ;
    private String role ;
    private String imageUrl ;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    private List<Contact> contacts = new ArrayList() ;

    public List<Contact> getContacts() {
        return contacts;
    }


    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    public User() {
    }


    public User(String name, String email, String password, String about, boolean enabled, String role,
            String imageUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.enabled = enabled;
        this.role = role;
        this.imageUrl = imageUrl;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        id = id;
    }
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
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

// Added to string 
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
                + ", enabled=" + enabled + ", role=" + role + ", imageUrl=" + imageUrl + ", contacts=" + contacts + "]";
    }

    
}
