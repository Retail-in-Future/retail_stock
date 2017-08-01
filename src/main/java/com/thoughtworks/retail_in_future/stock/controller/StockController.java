package com.thoughtworks.retail_in_future.stock.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.retail_in_future.stock.bean.Product;
import com.thoughtworks.retail_in_future.stock.bean.Stock;
import com.thoughtworks.retail_in_future.stock.bean.view.StockInfo;
import com.thoughtworks.retail_in_future.stock.exception.NotFoundException;
import com.thoughtworks.retail_in_future.stock.service.StockService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/stocks/")
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "View the stocks", response = List.class)
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> list(){

        List<Product> productList = stockService.findAllProduct();

        Map<String, Object> objectMap = new HashMap<>();

        List<StockInfo> stockList = productList
                .stream()
                .map(product -> {
                    StockInfo stockInfo = new StockInfo(product);
                    Stock stock = stockService.find(product.getId());
                    if(stock != null){
                        stockInfo.setAmount(stock.getAmount());
                        stockInfo.setPrice(stock.getPrice());
                    }
                    return stockInfo;
                })
                .sorted((e1, e2) -> Long.compare(e1.getSku(),e2.getSku()))
                .collect(toList());

        objectMap.put("result", 1);
        objectMap.put("data", stockList);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }


    @ApiOperation(value = "View the sku stock", response = ResponseEntity.class)
    @RequestMapping(value = "{sku}/", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity show(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku
    ) throws NotFoundException {
        Product product = stockService.findProductBySku(sku);

        Stock stock = stockService.find(sku);

        StockInfo stockInfo;

        if(stock != null){
            stock.setProduct(product);
            stockInfo = new StockInfo(stock);
        }else{
            stockInfo = new StockInfo(product);
        }

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", stockInfo);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

    @ApiOperation(value = "get the amount by sku", response = ResponseEntity.class)
    @RequestMapping(value = "{sku}/amount", method= RequestMethod.GET)
    public long getSku(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku
    ) throws NotFoundException {

        Stock stock = stockService.find(sku);

        if(stock != null){
            return stock.getAmount();
        }else{
            return 0;
        }
    }

    @ApiOperation(value = "create stock", response = Stock.class)
    @RequestMapping(value = "{sku}/", method= RequestMethod.POST, produces = "application/json")
    public Stock create(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku,
            @RequestBody Stock stock
    ){
        //TODO check the sku is existed in the Product
        return stockService.save(stock);
    }

    @ApiOperation(value = "update stock", response = Stock.class)
    @RequestMapping(value = "{sku}/stockOut", method= RequestMethod.PUT, produces = "application/json")
    public ResponseEntity increase(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku,
            @RequestParam(value="stockOut", required = false) Long stockOut

    ) throws NotFoundException {

        long amountOut = stockOut == null ? 1 : stockOut;
        stockService.updateStockOut(amountOut, sku);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", amountOut);

        return new ResponseEntity(objectMap, HttpStatus.OK);

    }

    @ApiOperation(value = "update amount", response = Stock.class)
    @RequestMapping(value = "{sku}/amount", method= RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateAmount(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku,
            @RequestParam(value="amount", required = false) Long amount

    ) throws NotFoundException {

        stockService.updateAmount(amount, sku);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", amount);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

    @ApiOperation(value = "update price", response = Stock.class)
    @RequestMapping(value = "{sku}/price", method= RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updatePrice(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku,
            @RequestParam(value="price", required = false) Float price

    ) throws NotFoundException {

        stockService.updatePrice(price, sku);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", price);

        return new ResponseEntity(objectMap, HttpStatus.OK);
    }


}
