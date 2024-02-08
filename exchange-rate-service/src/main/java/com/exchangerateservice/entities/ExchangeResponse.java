package com.exchangerateservice.entities;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeResponse {
    
    private String result ;
    private String provider ;
    private String documentation ;
    private String terms_of_use ;
    private String time_last_update_unix ;
    private String time_last_update_utc ;
    private String time_next_update_unix ;
    private String time_next_update_utc ;
    private String time_eol_unix ;
    private String base_code ;
    private HashMap<String,Double> rates ;
}
