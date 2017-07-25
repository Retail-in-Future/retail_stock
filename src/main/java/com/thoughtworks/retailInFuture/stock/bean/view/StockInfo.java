package com.thoughtworks.retailInFuture.stock.bean.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.retailInFuture.stock.bean.Product;

public class StockInfo {


    @JsonIgnore
    private Product product;
    private long sku;
    private String productName;
    private String productCode;

    @JsonProperty("amount")
    private String stock;
    @JsonProperty("stockOut")
    private String sold;
    @JsonProperty("price")
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
