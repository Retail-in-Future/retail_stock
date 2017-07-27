package com.thoughtworks.retail_in_future.stock.service;


import com.thoughtworks.retail_in_future.stock.bean.Stock;
import com.thoughtworks.retail_in_future.stock.exception.NotFoundException;
import com.thoughtworks.retail_in_future.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock find(long sku) throws NotFoundException {
        Stock stock = stockRepository.findFirstByProductId(sku);

        if(stock == null){
            throw  new NotFoundException("Not found product by the sku", sku);
        }

        return stock;

    }


    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public int stockOut(long stockOut, long sku) throws NotFoundException {

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
