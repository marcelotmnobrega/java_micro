package com.mtmn.javamicro.dbservice.quote;

import lombok.Data;

import java.util.List;

@Data
public class QuoteAddRequestBody {
    private String username;
    private List<String> quotes;
}
