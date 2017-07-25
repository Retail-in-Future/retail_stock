package com.thoughtworks.retailInFuture.stock.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Stock")
@Table(name = "stock")
@ApiModel(value="stockInfo")
public class Stock {

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private long id;

    @JsonIgnore
    private long productId;


    public Product product;

    private long stockOut;

    private long amount;

    private float price;


    @GenericGenerator(name = "generator", strategy = "foreign")
    @GeneratedValue(generator = "generator")
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
