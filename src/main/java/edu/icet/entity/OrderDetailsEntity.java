package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orderDetail")
public class OrderDetailsEntity {
    @EmbeddedId
    private OrderDetails_PkEntity id;

    private int quantity;
    private Double price;
    private Double discount;

    @ManyToOne
    @MapsId("orderId")
    private OrderEntity order;

    @ManyToOne
    @MapsId("itemCode")
    @JoinColumn(name = "code")
    private ItemEntity item;
}
