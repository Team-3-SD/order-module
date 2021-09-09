package fisi.order.module.application.product;

import java.util.List;

import fisi.order.module.config.MessagingConfig;
import fisi.order.module.web.models.OrderStatus;
import fisi.order.module.web.models.ProductDTO;
import fisi.order.module.web.models.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fisi.order.module.domain.product.model.Product;
import fisi.order.module.domain.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> productListAvailable() {
        return this.repository.productListAvailable();
    }

    @Override
    public List<Product> productListAll() {
        return this.repository.productListAll();
    }

    @Override
    public Product getProduct(int idProduct) {
        return this.repository.getProduct(idProduct);
    }

    @Override
    public ProductStatus addProduct(Product product) {
        int id = this.repository.addProduct(product);
        if (id != 0) {
            product.setId(id);
            ProductStatus productStatus = new ProductStatus(product, "GUARDADO",
                    "Producto guardado");
            return productStatus;
        }
        return null;
    }

    @Override
    public ProductStatus updateProduct(Product product) {
        int orderId = this.repository.updateProduct(product);
        if (orderId != 0) {
            product.setId(orderId);
            ProductStatus productStatus = new ProductStatus(product, "ACTUALIZADO",
                    "Producto actualizado");
            return productStatus;
        }
        return null;
    }

    @Override
    public ProductStatus deleteProduct(int idProduct) {
        int id = this.repository.deleteProduct(idProduct);
        if (id != 0) {
            ProductStatus productStatus = new ProductStatus(Product.builder().id(idProduct).build(), "ELIMINADO",
                    "Producto eliminado");
            return productStatus;
        }
        return null;
    }

}
