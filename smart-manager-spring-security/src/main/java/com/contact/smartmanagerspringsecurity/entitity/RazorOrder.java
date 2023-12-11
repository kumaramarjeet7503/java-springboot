package com.contact.smartmanagerspringsecurity.entitity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class RazorOrder {

      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      private Integer id ;
      private int  amount ;
      private String  orderId ;
      private String  status ;
      private String  receipt ;
      
      @ManyToOne
      private User user ;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public int getAmount() {
            return amount;
      }

      public void setAmount(int amount) {
            this.amount = amount;
      }

      public String getOrderId() {
            return orderId;
      }

      public void setOrderId(String orderId) {
            this.orderId = orderId;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }

      public String getReceipt() {
            return receipt;
      }

      public void setReceipt(String receipt) {
            this.receipt = receipt;
      }

      public User getUser() {
            return user;
      }

      public void setUser(User user) {
            this.user = user;
      }
        
}
