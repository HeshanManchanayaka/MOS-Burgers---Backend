package edu.icet.dto;

import jakarta.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String orderId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private Integer totalItems;
    private String status;
    private Long customerId;

    private Customer customer;
    private List<OrderDetails> orderDetails;
}
