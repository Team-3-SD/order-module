package fisi.order.module.web.controller;

import fisi.order.module.application.product.ProductService;
import fisi.order.module.application.resource.IUploadFileService;
import fisi.order.module.domain.product.model.Product;
import fisi.order.module.web.models.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/products"})
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping
    public List<Product> productList() {
        return this.service.productListAvailable();
    }

    @GetMapping(value = "/all")
    public List<Product> productListAll() {
        return this.service.productListAll();
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {
        Product product = this.service.getProduct(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductStatus> addProduct(@RequestParam(name = "image", required = false) MultipartFile image,
                                                    @RequestParam("name") String name,
                                                    @RequestParam("brand") String brand,
                                                    @RequestParam("price") String price,
                                                    @RequestParam("stock") String stock,
                                                    @RequestParam("description") String description,
                                                    @RequestParam("state") String state,
                                                    HttpServletRequest request) {
        try {
            uploadFileService.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uniqueFilename = "";

        try {
            uniqueFilename = uploadFileService.copy(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueFilename);
        return ResponseEntity.ok().body(this.service.addProduct(Product.builder()
                .name(name)
                .brand(brand)
                .price(Double.parseDouble(price))
                .image(uniqueFilename)
                .stock(Integer.parseInt(stock))
                .description(description)
                .state(Integer.parseInt(state))
                .build()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductStatus> updateProduct(@PathVariable int id, @RequestParam("image") MultipartFile image,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("brand") String brand,
                                                       @RequestParam("price") String price,
                                                       @RequestParam("stock") String stock,
                                                       @RequestParam("description") String description,
                                                       @RequestParam("state") String state) {
        try {
            uploadFileService.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = this.service.getProduct(id);
        String uniqueFilename = "";
        System.out.println(image.getOriginalFilename());
        if (product.getImage().equalsIgnoreCase(image.getOriginalFilename())) {
            uniqueFilename = product.getImage();
        } else {
            uploadFileService.delete(product.getImage());
            try {
                uniqueFilename = uploadFileService.copy(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(uniqueFilename);
        }
        return ResponseEntity.ok().body(this.service.updateProduct(Product.builder()
                .id(id)
                .name(name)
                .brand(brand)
                .price(Double.parseDouble(price))
                .image(uniqueFilename)
                .stock(Integer.parseInt(stock))
                .description(description)
                .state(Integer.parseInt(state))
                .build()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductStatus> deleteProduct(@PathVariable int id) {
        try {
            uploadFileService.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product productDelete = this.service.getProduct(id);
        uploadFileService.delete(productDelete.getImage());
        return ResponseEntity.ok().body(this.service.deleteProduct(id));
    }
}
