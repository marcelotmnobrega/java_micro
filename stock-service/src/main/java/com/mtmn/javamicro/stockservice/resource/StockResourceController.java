package com.mtmn.javamicro.stockservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stockprice")
public class StockResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<StockTickerPriceDTO> getStockPrices(@PathVariable("username") final String username){
        String userTickersByUsernameURL = "http://db-service/rest/db/userticker/" + username;
        ResponseEntity<List<String>> userTickersResponse =
                restTemplate.exchange(userTickersByUsernameURL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<String>>() {});
        List<String> tickers = userTickersResponse.getBody();
        List<StockTickerPriceDTO> result = tickers
                .stream()
                .map(ticker -> new StockTickerPriceDTO(ticker, getStockPrice(ticker)))
                .collect(Collectors.toList());
        return result;
    }

    private BigDecimal getStockPrice(String ticker) {
        try {
            return YahooFinance.get(ticker).getQuote().getPrice();
        } catch (IOException e) {
            e.printStackTrace();
            return BigDecimal.valueOf(-1);
        }
    }
}
