package com.thoughtworks.retailInFuture.stock.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.retailInFuture.stock.bean.view.StockInfo;
import com.thoughtworks.retailInFuture.stock.service.StockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/stocks/")
public class StockController {

    @Autowired
    private StockService stockService;

    @ApiOperation(value = "View the stocks", response = List.class)
    @RequestMapping(method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, Object>> list(){
        List<StockInfo> stockInfoList = stockService.findAll().
                stream().map(r -> new ObjectMapper().convertValue(r, StockInfo.class))
                .collect(toList());
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("result", 1);
        objectMap.put("data", stockInfoList);
        return new ResponseEntity(objectMap, HttpStatus.OK);
    }

}
