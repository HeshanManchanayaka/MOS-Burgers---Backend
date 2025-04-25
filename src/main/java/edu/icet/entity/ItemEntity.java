package edu.icet.entity;

import edu.icet.util.ItemCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="item")
public class ItemEntity {
    @Id
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
    private Integer stock;
    private Double price;
    private Double discount;

    @OneToMany(mappedBy = "Item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List< OrderDetailsEntity> orderDetails;

}