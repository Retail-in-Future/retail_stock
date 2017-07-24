package com.thoughtworks.retailInFuture.stock.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(value="stockInfo")
public class Stock {

    @ApiModelProperty(hidden = true)
    private long id;

    private String sku;

    private String unit;

    private long amount;

    private float price;


    private long stockOut;

    public Stock() {
    }

    public Stock(long id, String sku, String unit, long amount, float price, long stockOut) {
        this.id = id;
        this.sku = sku;
        this.unit = unit;
        this.amount = amount;
        this.price = price;
        this.stockOut = stockOut;
    }

    public Stock(String sku, String unit, long amount, float price, long stockOut) {
        this.sku = sku;
        this.unit = unit;
        this.amount = amount;
        this.price = price;
        this.stockOut = stockOut;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name="stockOut")
    public long getStockOut() {
        return stockOut;
    }

    public void setStockOut(long stockOut) {
        this.stockOut = stockOut;
    }
}
