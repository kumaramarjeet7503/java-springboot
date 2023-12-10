package com.contact.smartmanagerspringsecurity.controllers;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@RestController
@RequestMapping("/order")
public class OrderController {
    
    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@RequestBody Map<String, Object> data) throws RazorpayException {

        System.out.println(data.get("paymentField") );
        String paymentField = (String) data.get("paymentField") ;
        int amount =  Integer.parseInt(paymentField) ;
        RazorpayClient razorpay = new RazorpayClient("rzp_test_35qE5WllMM5aQu", "hbi7tdqHqFrYy5D81ZkrOEPB");
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100); // amount in the smallest currency unit
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_rcptid_11");

        Order order = razorpay.orders.create(orderRequest);

        System.out.println(order);
        String orderId = order.get("id");
        return ResponseEntity.ok(order.toString());
    }


    
}
