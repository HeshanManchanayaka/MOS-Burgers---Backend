package edu.icet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    private Long orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private Integer totalItems;
    private Double totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id" ,  nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailsEntity> orderDetails;
}
