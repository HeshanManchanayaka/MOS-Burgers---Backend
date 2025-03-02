package edu.icet.dto;

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
    private Long orderId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String status;
    private Integer customerId;
    private List<OrderItem> orderItems;
}
