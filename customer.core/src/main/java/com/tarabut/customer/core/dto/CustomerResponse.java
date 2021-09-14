package com.tarabut.customer.core.dto;



import java.util.List;

public class CustomerResponse<T> extends BaseResponse {
    private String id;

    public List<T> getCustomers() {
        return customers;
    }

    public void setCustomers(List<T> customers) {
        this.customers = customers;
    }

    private List<T> customers;


    public CustomerResponse(String id, String message) {
        super(message);
        this.id = id;
    }

    public CustomerResponse(String message, List<T> customers) {
        super(message);
        this.customers = customers;
    }

    public CustomerResponse(String message) {
        super(message);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
