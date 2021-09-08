package fisi.order.module.application.brand;

import fisi.order.module.domain.brand.model.Brand;
import fisi.order.module.web.models.BrandDTO;
import fisi.order.module.web.models.BrandStatus;

import java.util.List;

public interface BrandService {

    BrandStatus addBrand(Brand brand);

    Brand getBrand(int idBrand);

    BrandStatus updateBrand(int id, Brand brand);

    BrandStatus deleteBrand(int idBrand);

    List<Brand> getBrands();
}
