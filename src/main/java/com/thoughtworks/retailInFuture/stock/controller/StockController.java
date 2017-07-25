package com.thoughtworks.retailInFuture.stock.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.retailInFuture.stock.bean.view.StockInfo;
import com.thoughtworks.retailInFuture.stock.service.StockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/stocks/")
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "View the stocks", response = List.class)
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public List<StockInfo> list(){
        return stockService.findAll().stream().map(r -> new ObjectMapper().convertValue(r, StockInfo.class)).collect(toList());

    }

}
