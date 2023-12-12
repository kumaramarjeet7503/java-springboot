package com.springbootdataimport.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.springbootdataimport.model.OrderImport;

public class OrderItemProcessor  implements ItemProcessor <OrderImport, OrderImport> {

    @Override
    @Nullable
    public OrderImport process(@NonNull OrderImport orderImport) throws Exception {
        return orderImport ;
    }
    
    
}
