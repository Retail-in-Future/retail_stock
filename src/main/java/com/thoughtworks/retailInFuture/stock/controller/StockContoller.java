package com.thoughtworks.retailInFuture.stock.controller;

import com.thoughtworks.retailInFuture.stock.bean.StockInfo;
import com.thoughtworks.retailInFuture.stock.service.StockService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StockContoller {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/products/{sku}/stock", method = RequestMethod.GET)
    public StockInfo show(
            @ApiParam(value = "the product sku", required = true)
            @PathVariable("sku") String sku
    ){
        return stockService.find(sku);
    }

    @RequestMapping(value = "/products/{sku}/stock", method = RequestMethod.POST)
    public StockInfo create(
            @ApiParam(value = "the product sku", required = true)
            @PathVariable("sku") String sku,
            @RequestBody StockInfo stockInfo
    ){

        //TODO check the sku is existed in the Product

        return stockService.save(stockInfo);
    }
    
}
