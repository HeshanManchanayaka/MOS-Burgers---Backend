package edu.icet.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDetails_PkEntity implements Serializable {
    private String orderId;
    private  String itemCode;
}
