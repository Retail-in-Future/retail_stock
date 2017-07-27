package com.thoughtworks.retail_in_future.stock.bean.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.retail_in_future.stock.bean.Product;

public class StockInfo {


    @JsonIgnore
    private Product product;

    @JsonProperty("SKU")
    private long sku;
    private String productName;
    private String productCode;

    private String amount;
    private String stockOut;
    private float price;

    @JsonProperty("product")
    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getSku() {
        return product.getId();
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return product.getName();

    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return product.getCode();
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStockOut() {
        return stockOut;
    }

    public void setStockOut(String stockOut) {
        this.stockOut = stockOut;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
