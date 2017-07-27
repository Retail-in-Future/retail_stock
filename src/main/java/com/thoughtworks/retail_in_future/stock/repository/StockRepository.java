package com.thoughtworks.retail_in_future.stock.repository;

import com.thoughtworks.retail_in_future.stock.bean.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    Stock findFirstByProductId(long sku);

    Stock save(Stock stock);

    @Modifying
    @Transactional
    @Query("update Stock s set s.stockOut = s.stockOut + ?1 where s.productId= ?2")
    int stockOutBySku(long stockOut, long sku);
}
