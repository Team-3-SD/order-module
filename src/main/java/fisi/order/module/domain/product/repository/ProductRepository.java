package fisi.order.module.domain.product.repository;

import java.util.List;

import fisi.order.module.domain.product.model.Product;
import fisi.order.module.web.models.OrderDTO;
import fisi.order.module.web.models.ProductDTO;

public interface ProductRepository {
    List<Product> productListAvailable();

    List<Product> productListAll();

    Product getProduct(int productId);

    int addProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(int idProduct);
}
