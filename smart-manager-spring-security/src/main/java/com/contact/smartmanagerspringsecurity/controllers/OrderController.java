package com.contact.smartmanagerspringsecurity.controllers;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.smartmanagerspringsecurity.dao.ContactRepository;
import com.contact.smartmanagerspringsecurity.dao.OrderRepository;
import com.contact.smartmanagerspringsecurity.dao.UserRepository;
import com.contact.smartmanagerspringsecurity.entitity.RazorOrder;
import com.contact.smartmanagerspringsecurity.entitity.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@RestController
@RequestMapping("/order")
public class OrderController {
    
        @Autowired
    private UserRepository userRepository ;
        @Autowired
    private OrderRepository orderRepository ;

    @ModelAttribute
    public void getUser(Model model,Principal principal)
    {
        String userName = principal.getName() ;
        User user = this.userRepository.getUserByUserName(userName) ;
        model.addAttribute("user",user) ;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postMethodName(@RequestBody Map<String, Object> data, Model model) throws RazorpayException {
        String paymentField = (String) data.get("paymentField") ;
        int amount =  Integer.parseInt(paymentField) ;

        //  Create order API 
        RazorpayClient razorpay = new RazorpayClient("rzp_test_35qE5WllMM5aQu", "hbi7tdqHqFrYy5D81ZkrOEPB");
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount*100); // amount in the smallest currency unit
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "order_rcptid_11");

        Order order = razorpay.orders.create(orderRequest);

        int amountCap = order.get("amount") ;

        //  Capture order response
        RazorOrder rOrder = new RazorOrder() ;
        rOrder.setAmount(amountCap/100)  ;
        rOrder.setOrderId(order.get("id")) ;
        rOrder.setStatus(order.get("status")) ;
        rOrder.setReceipt(order.get("receipt")) ;

        User user = (User) model.getAttribute("user") ;
        rOrder.setUser(user) ;
        this.orderRepository.save(rOrder) ;

        return ResponseEntity.ok(order.toString());
    }


    
}
