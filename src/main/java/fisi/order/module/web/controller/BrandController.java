package fisi.order.module.web.controller;

import fisi.order.module.application.brand.BrandService;
import fisi.order.module.domain.brand.model.Brand;
import fisi.order.module.domain.product.model.Product;
import fisi.order.module.web.models.BrandDTO;
import fisi.order.module.web.models.BrandStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/brands"})
public class BrandController {

    @Autowired
    private BrandService service;

    @GetMapping()
    public ResponseEntity<List<Brand>> brandListAll() {
        return ResponseEntity.ok().body(this.service.getBrands());
    }

    @GetMapping(value = "/{brandId}")
    public ResponseEntity<Brand> getBrand(@PathVariable int brandId) {
        return ResponseEntity.ok().body(this.service.getBrand(brandId));
    }

    @DeleteMapping(value = "/{brandId}")
    public ResponseEntity<BrandStatus> deleteBrand(@PathVariable int brandId) {
        return ResponseEntity.ok().body(this.service.deleteBrand(brandId));
    }

    @PostMapping()
    public ResponseEntity<BrandStatus> addBrand(@RequestBody BrandDTO brand) {

        return ResponseEntity.ok().body(this.service.addBrand(Brand.builder().name(brand.getName()).build()));
    }

    @PutMapping(value = "/{brandId}")
    public ResponseEntity<BrandStatus> addBrand(@PathVariable int brandId, @RequestBody Brand brand) {
        return ResponseEntity.ok().body(this.service.updateBrand(brandId, brand));
    }
}
