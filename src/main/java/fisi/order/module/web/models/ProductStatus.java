package fisi.order.module.web.models;

import fisi.order.module.domain.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStatus {
    private Product product;
    private String status;
    private String message;
}
