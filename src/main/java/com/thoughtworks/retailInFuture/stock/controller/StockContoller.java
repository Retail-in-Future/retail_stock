package com.thoughtworks.retailInFuture.stock.controller;

import com.thoughtworks.retailInFuture.stock.bean.StockInfo;
import com.thoughtworks.retailInFuture.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{sku}/stock")

@Api(value = "stock management", description = "The stock management of Retail in future")

public class StockContoller {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "View the sku stock", response = StockInfo.class)
    @RequestMapping(method= RequestMethod.GET,produces = "application/json")
    public StockInfo show(
            @ApiParam(value = "the product sku", required = true)
            @PathVariable("sku") String sku
    ){
        return stockService.find(sku);
    }

    @ApiOperation(value = "create stock", response = StockInfo.class)
    @RequestMapping(method= RequestMethod.POST,produces = "application/json")
    public StockInfo create(
            @ApiParam(value = "the product sku", required = true)
            @PathVariable("sku") String sku,
            @RequestBody StockInfo stockInfo
    ){

        //TODO check the sku is existed in the Product

        return stockService.save(stockInfo);
    }

    
}
