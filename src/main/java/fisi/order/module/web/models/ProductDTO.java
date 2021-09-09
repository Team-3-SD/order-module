package fisi.order.module.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int id;
    private String name;
    private String brand;
    private double price;
    private String image;
    private int stock;
    private String description;
    private int state;
}
