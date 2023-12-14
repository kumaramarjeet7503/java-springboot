package com.springbootgeneratedocument.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.poifs.storage.HeaderBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgeneratedocument.dao.OrderRepository;
import com.springbootgeneratedocument.entitiy.Order;
import com.springbootgeneratedocument.helper.ExcelHelper;

@Service
public class ExcelService {
    
    @Autowired
    private OrderRepository orderRepository ;

    public ByteArrayInputStream getData() throws IOException{
        List<Order> orders = orderRepository.findAll() ;
        ExcelHelper helper = new ExcelHelper() ;
        return helper.getData(orders) ;
    }
}
