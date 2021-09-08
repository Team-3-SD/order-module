package fisi.order.module.application.product;

import java.util.List;

import fisi.order.module.domain.product.model.Product;
import fisi.order.module.web.models.OrderDTO;
import fisi.order.module.web.models.OrderStatus;
import fisi.order.module.web.models.ProductDTO;
import fisi.order.module.web.models.ProductStatus;

public interface ProductService {
    List<Product> productListAvailable();

    List<Product> productListAll();

    Product getProduct(int idProduct);

    ProductStatus addProduct(Product product);

    ProductStatus updateProduct(Product product);

    ProductStatus deleteProduct(int idProduct);
}
