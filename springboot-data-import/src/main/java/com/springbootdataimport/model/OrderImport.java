package com.springbootdataimport.model;


public class OrderImport {

    private String id ;
    private String street ;
    private String name ;
    private String roll ;
    private Double balance ;
    private Double amount ;
    private Double commision ;
    private String city ;
    private String item ;
    private Double tax ;
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRoll() {
        return roll;
    }
    public void setRoll(String roll) {
        this.roll = roll;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getCommision() {
        return commision;
    }
    public void setCommision(Double commision) {
        this.commision = commision;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public Double getTax() {
        return tax;
    }
    public void setTax(Double tax) {
        this.tax = tax;
    }
}
