package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Item {
    private String code;
    private String name;
    private String category;
    private Integer stock;
    private Double price;
    private Double discount;
    private List<String> orderIds;
}
