package fisi.order.module.domain.brand.repository;

import fisi.order.module.domain.brand.model.Brand;

import java.util.List;

public interface BrandRepository {
    int addBrand(Brand brand);

    int updateBrand(Brand brand);

    int deleteProduct(int idBrand);

    List<Brand> brandList();

    Brand getBrand(int idBrand);
}
