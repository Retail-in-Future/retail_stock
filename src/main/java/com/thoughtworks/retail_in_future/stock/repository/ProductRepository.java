package com.thoughtworks.retail_in_future.stock.repository;

import com.thoughtworks.retail_in_future.stock.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by xyduan on 7/28/17.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
