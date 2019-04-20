package com.mtmn.javamicro.stockservice.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTickerPriceDTO {

    @JsonProperty("ticker")
    private String stockTicker;
    private BigDecimal price;
}
