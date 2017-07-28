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

        List<StockInfo> allProduct = new ArrayList<>();
        List<Stock> stockList = stockService.findAll();

        List<Long> productIds = stockList.stream().map(s -> s.getProductId()).collect(toList());
        List<Product> productList = stockService.findAllProduct();

        List<StockInfo> productWithoutStock = productList.stream().filter(s -> !productIds.contains(s.getId())).map(product -> new StockInfo(product)).collect(toList());

        List<StockInfo> productWithStock = stockList.
                stream().map(stock -> new StockInfo(stock))
                .collect(toList());


        allProduct.addAll(productWithoutStock);
        allProduct.addAll(productWithStock);

        Map<String, Object> objectMap = new HashMap<>();

        List<StockInfo> stockListResult = allProduct.stream()
                .sorted((e1, e2) -> Long.compare(e1.getSku(),
                        e2.getSku())).collect(toList());

        objectMap.put("result", 1);
        objectMap.put("data", stockListResult);


        return new ResponseEntity(objectMap, HttpStatus.OK);
    }


    @ApiOperation(value = "View the sku stock", response = ResponseEntity.class)
    @RequestMapping(value = "{sku}/", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity show(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku
    ) throws NotFoundException {

        Stock stock = stockService.find(sku);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", new ObjectMapper().convertValue(stock, StockInfo.class));

        return new ResponseEntity(objectMap, HttpStatus.OK);

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
    @RequestMapping(value = "{sku}/", method= RequestMethod.PUT, produces = "application/json")
    public int increase(
            @ApiParam(value = "sku", required = true)
            @PathVariable("sku") long sku,
            @RequestParam(value="stockOut", required = false) Long stockOut

    ) throws NotFoundException {

        //TODO check the sku is existed in the Product
        long outAmount = stockOut == null ? 1 : stockOut;
        return stockService.stockOut(outAmount, sku);
    }


}
