package edu.icet.dto;


import lombok.Data;

@Data
public class OrderDetails {

    private String productId;
    private String orderId;
    private Integer orderQty;
    private Double price;
    private Double discount;
}
