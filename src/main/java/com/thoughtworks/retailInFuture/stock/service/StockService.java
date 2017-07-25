package com.thoughtworks.retailInFuture.stock.service;


import com.thoughtworks.retailInFuture.stock.bean.Stock;
import com.thoughtworks.retailInFuture.stock.exception.NotFoundException;
import com.thoughtworks.retailInFuture.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock find(String sku) {
        return stockRepository.findFirstByProductId(sku);
    }


    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public int stockOut(long stockOut, String sku) throws NotFoundException {

        Stock stock = find(sku);
        if(stock == null){
            throw new NotFoundException("NOT FOUND SKU", sku);
        }

        return stockRepository.stockOutBySku(stockOut, sku);

    }

    public java.util.List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
