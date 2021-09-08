package fisi.order.module.web.models;

import fisi.order.module.domain.brand.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandStatus {
    private Brand brand;
    private String status;
    private String message;
}
