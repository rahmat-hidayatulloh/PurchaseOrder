package com.PurchaseOrder.PurchaseOrderApi.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(int id, String key) {
        super(key +" id not found : " + id);
    }
}
