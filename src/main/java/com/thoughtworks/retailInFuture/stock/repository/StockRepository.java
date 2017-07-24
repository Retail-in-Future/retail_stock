package com.thoughtworks.retailInFuture.stock.repository;

import com.thoughtworks.retailInFuture.stock.bean.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    Stock findFirstBySku(String sku);

    Stock save(Stock stock);

    @Modifying
    @Transactional
    @Query("update Stock s set s.stockOut = s.stockOut + ?1 where s.sku = ?2")
    int stockOutBySku(long stockOut, String sku);
}
