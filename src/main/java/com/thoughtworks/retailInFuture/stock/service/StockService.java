package com.thoughtworks.retailInFuture.stock.service;


import com.thoughtworks.retailInFuture.stock.bean.StockInfo;
import com.thoughtworks.retailInFuture.stock.exception.NotFoundException;
import com.thoughtworks.retailInFuture.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockInfo find(String sku) {
        return stockRepository.findFirstBySku(sku);
    }


    public StockInfo save(StockInfo stockInfo) {
        return stockRepository.save(stockInfo);
    }

}
