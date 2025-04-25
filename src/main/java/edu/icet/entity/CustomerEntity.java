package edu.icet.entity;

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
@Table(name="Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer loyalty;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<OrderEntity> orders;
}
