package com.thoughtworks.retailInFuture.stock.repository;

import com.thoughtworks.retailInFuture.stock.bean.StockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockInfo, Long>{

    StockInfo findFirstBySku(String sku);
    StockInfo save(StockInfo stockInfo);
}
