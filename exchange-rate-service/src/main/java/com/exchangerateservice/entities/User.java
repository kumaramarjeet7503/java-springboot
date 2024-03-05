package com.exchangerateservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;


@Document(collection="client")
@Data
@AllArgsConstructor
public class User {
    
    @Id
    private String id ;
    private String email ;
    private String name ;
    private String password ;
    private String roles ;
}
