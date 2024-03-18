package com.websocket.entity;

import lombok.Getter;

@Getter
public class OutputMessage {
    private String name ;
    private String email ;

    public OutputMessage(final String name, final String time){
        this.name = name ;
        this.email = email ;
    }

}
