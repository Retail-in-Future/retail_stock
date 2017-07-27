package com.thoughtworks.retail_in_future.stock.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.retail_in_future.stock.bean.Product;
import com.thoughtworks.retail_in_future.stock.bean.Stock;
import com.thoughtworks.retail_in_future.stock.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StockController.class})
public class StockControllerTest {

    @InjectMocks
    StockController stockController;

    @Mock
    StockService stockService;

    @Test
    public void shouldReturnAllStockList(){

        doReturn(createStockInfos()).when(stockService).findAll();

        ResponseEntity<Map<String, Object>> responseEntity = stockController.list();

        assertEquals(200, responseEntity.getStatusCodeValue());

        Map<String, Object> body = responseEntity.getBody();

        assertEquals(1, body.get("result"));

        List data = (List)body.get("data");

        assertEquals(3, data.size());

        Map object = new ObjectMapper().convertValue(data.get(0), Map.class);

        System.out.println(object);
        assertEquals("12345", object.get("SKU").toString());
        assertEquals("cat", object.get("productName"));
        assertEquals("cat---1", object.get("productCode"));
        assertEquals("100", object.get("amount").toString());
        assertEquals("0", object.get("stockOut").toString());
        assertEquals("1.0", object.get("price").toString());


    }

    private List<Stock> createStockInfos() {

        return Arrays.asList(
                createStock(12345, "cat", "cat---1", 100, 0, 1),
                createStock(123456, "dog", "dog---1", 100, 0, 2),
                createStock(1234567, "cloth", "cloth---1", 100, 0, 2)
        );
    }

    private Stock createStock(long sku, String productName, String productCode, long amount, long stockOut, float price){
        Stock stock = new Stock();
        stock.setAmount(amount);
        stock.setPrice(price);
        stock.setStockOut(stockOut);

        Product product = new Product();
        product.setId(sku);
        product.setCode(productCode);
        product.setName(productName);

        stock.setProduct(product);

        return stock;
    }
}