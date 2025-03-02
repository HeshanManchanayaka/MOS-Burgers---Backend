package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItemEntity {
    @Id
    private String itemCode;
    private Integer quentity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;
}
