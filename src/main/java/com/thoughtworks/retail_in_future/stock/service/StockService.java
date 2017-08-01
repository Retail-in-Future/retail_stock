package com.thoughtworks.retail_in_future.stock.service;


import com.thoughtworks.retail_in_future.stock.bean.Product;
import com.thoughtworks.retail_in_future.stock.bean.Stock;
import com.thoughtworks.retail_in_future.stock.exception.NotFoundException;
import com.thoughtworks.retail_in_future.stock.repository.ProductRepository;
import com.thoughtworks.retail_in_future.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    public Stock find(long sku) {
        return stockRepository.findFirstByProductId(sku);
    }


    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public int updateStockOut(long stockOut, long sku) throws NotFoundException {

        findOrCreateBySku(sku);

        return stockRepository.setStockOutBySku(stockOut, sku);

    }

    public java.util.List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public Product findProductBySku(long sku) throws NotFoundException {


        Product product = productRepository.findFirstById(sku);

        if(product == null){
            throw  new NotFoundException("Not found product by the sku", sku);
        }

        return product;
    }

    public int updateAmount(Long amount, long sku) throws NotFoundException {

        findOrCreateBySku(sku);

        return stockRepository.setAmountBySku(amount, sku);
    }

    private void findOrCreateBySku(long sku) throws NotFoundException {

        Stock stock = find(sku);

        if(stock == null){
            Product productBySku = findProductBySku(sku);
            stock = new Stock();
            stock.setProductId(sku);
            stock.setProduct(productBySku);
            save(stock);
        }

    }


    public int updatePrice(Float price, long sku) throws NotFoundException {

        findOrCreateBySku(sku);

        return stockRepository.setPriceBySku(price, sku);
    }
}
