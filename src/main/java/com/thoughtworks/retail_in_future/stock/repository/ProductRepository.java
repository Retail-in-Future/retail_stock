package com.thoughtworks.retail_in_future.stock.repository;

import com.thoughtworks.retail_in_future.stock.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findFirstById(long sku);
}
