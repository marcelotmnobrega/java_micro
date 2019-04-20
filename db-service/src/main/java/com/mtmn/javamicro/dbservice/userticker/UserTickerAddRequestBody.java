package com.mtmn.javamicro.dbservice.userticker;

import lombok.Data;

import java.util.List;

@Data
public class UserTickerAddRequestBody {
    private String username;
    private List<String> tickers;
}
