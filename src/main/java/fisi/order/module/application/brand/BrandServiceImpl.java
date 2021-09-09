package fisi.order.module.application.brand;

import fisi.order.module.domain.brand.model.Brand;
import fisi.order.module.domain.brand.repository.BrandRepository;
import fisi.order.module.domain.product.model.Product;
import fisi.order.module.web.models.BrandStatus;
import fisi.order.module.web.models.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository repository;

    @Override
    public BrandStatus addBrand(Brand brand) {
        int id = this.repository.addBrand(brand);
        if (id != 0) {
            brand.setId(id);
            return new BrandStatus(brand, "GUARDADA",
                    "Marca guardada");
        }
        return null;
    }

    @Override
    public Brand getBrand(int idBrand) {
        return repository.getBrand(idBrand);
    }

    @Override
    public BrandStatus updateBrand(int idBrand, Brand brand) {
        int id = this.repository.updateBrand(brand);
        if (id != 0) {
            brand.setId(id);
            return new BrandStatus(brand, "ACTUALIZADA",
                    "Marca actaulizada");
        }
        return null;
    }

    @Override
    public BrandStatus deleteBrand(int idBrand) {
        int id = repository.deleteProduct(idBrand);
        if (id != 0) {
            return new BrandStatus(Brand.builder().id(id).build(), "ELIMINADA",
                    "Marca eliminada");
        }
        return null;
    }

    @Override
    public List<Brand> getBrands() {
        return repository.brandList();
    }
}
