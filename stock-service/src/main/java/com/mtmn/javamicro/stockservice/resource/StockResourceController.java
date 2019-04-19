package com.mtmn.javamicro.stockservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<Stock> getStock(@PathVariable("username") final String username){
        String url = "http://localhost:8300/rest/db/" + username;
        ResponseEntity<List<String>> quoteResponse =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
        List<String> quotes = quoteResponse.getBody();
        List<Stock> result = quotes
                .stream()
                .map(this::getStockPrice)
                .collect(Collectors.toList());
        return result;
    }

    private Stock getStockPrice(String ticker) {
        try {
            return YahooFinance.get(ticker);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(ticker);
        }
    }
}
