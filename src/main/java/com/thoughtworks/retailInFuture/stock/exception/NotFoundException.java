package com.thoughtworks.retailInFuture.stock.exception;

/**
 * Created by xyduan on 7/19/17.
 */
public class NotFoundException extends Exception {

    public NotFoundException(String s, String sku) {
        super(s + sku);
    }
}
