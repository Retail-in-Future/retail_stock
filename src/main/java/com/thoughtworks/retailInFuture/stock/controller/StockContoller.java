package com.thoughtworks.retailInFuture.stock.controller;

import com.thoughtworks.retailInFuture.stock.bean.Stock;
import com.thoughtworks.retailInFuture.stock.exception.NotFoundException;
import com.thoughtworks.retailInFuture.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products/{sku}/stock/")
@Api(value = "stock management", description = "The stock management of Retail in future")
public class StockContoller {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "View the sku stock", response = Stock.class)
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public Stock show(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") String sku
    ){
        return stockService.find(sku);
    }

    @ApiOperation(value = "create stock", response = Stock.class)
    @RequestMapping(method= RequestMethod.POST, produces = "application/json")
    public Stock create(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") String sku,
            @RequestBody Stock stock
    ){
        //TODO check the sku is existed in the Product
//        stock.setId(Math.abs(new Random().nextLong()));
        return stockService.save(stock);
    }

    @ApiOperation(value = "update stock", response = Stock.class)
    @RequestMapping(method= RequestMethod.PUT, produces = "application/json")
    public int increase(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") String sku,
            @RequestParam(value="stockOut", required = false) Long stockOut

    ) throws NotFoundException {

        //TODO check the sku is existed in the Product
        long outAmount = stockOut == null ? 1 : stockOut;
        return stockService.stockOut(outAmount, sku);
    }
    
}
