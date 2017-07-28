package com.thoughtworks.retail_in_future.stock.bean.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.retail_in_future.stock.bean.Product;
import com.thoughtworks.retail_in_future.stock.bean.Stock;

public class StockInfo {


    @JsonProperty("SKU")
    private long sku;
    private String productName;
    private String productCode;

    private long amount;
    private long stockOut;
    private float price;

    public StockInfo() {
    }

    public StockInfo(Product product) {
        this.sku = product.getId();
        this.productName = product.getName();
        this.productCode = product.getCode();
    }


    public StockInfo(Stock stock) {
        Product product = stock.getProduct();
        this.sku = product.getId();
        this.productName = product.getName();
        this.productCode = product.getCode();
        this.price = stock.getPrice();
        this.amount = stock.getAmount();
        this.stockOut = stock.getStockOut();
        this.price = stock.getPrice();
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getStockOut() {
        return stockOut;
    }

    public void setStockOut(long stockOut) {
        this.stockOut = stockOut;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
