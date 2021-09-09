package fisi.order.module.infraestructure.product.jdbc;

import fisi.order.module.domain.product.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductRowMapper implements RowMapper<Product> {

    public List<Product> mapRow(List<Map<String, Object>> rows) {
        List<Product> list = new ArrayList<Product>();
        for (Map<String, Object> row : rows) {
            int id = Integer.parseInt(row.get("CPRODUCT").toString());
            String name = row.get("PRODUCT_NAME").toString();
            String brand = row.get("MARCA_NAME").toString();
            String image = row.get("PRODUCT_IMAGE").toString();
            double price = (double) Double.parseDouble(row.get("PRODUCT_PRECIO").toString());
            list.add(Product.builder().id(id).name(name).brand(brand).price(price).image(image).build());
        }
        return list;
    }

    public List<Product> mapRowAll(List<Map<String, Object>> rows) {
        List<Product> listProduct = new ArrayList<Product>();
        for (Map<String, Object> row : rows) {
            int id = Integer.parseInt(row.get("CPRODUCT").toString());
            String name = row.get("PRODUCT_NAME").toString();
            String brand = row.get("MARCA_NAME").toString();
            String image = row.get("PRODUCT_IMAGE").toString();
            double price = (double) Double.parseDouble(row.get("PRODUCT_PRECIO").toString());
            int stock = (int) Integer.parseInt(row.get("STOCK").toString());
            String description = row.get("PRODUCT_DESCRIPCION").toString();
            int state = (int) Integer.parseInt(row.get("BTKESTADO").toString());
            listProduct.add(Product.builder()
                    .id(id)
                    .name(name)
                    .brand(brand)
                    .price(price)
                    .image(image)
                    .stock(stock)
                    .description(description)
                    .state(state)
                    .build());
        }
        return listProduct;
    }

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("CPRODUCT");
        String name = resultSet.getString("PRODUCT_NAME");
        String brand = resultSet.getString("MARCA_NAME");
        String image = resultSet.getString("PRODUCT_IMAGE");
        double price = resultSet.getDouble("PRODUCT_PRECIO");
        int stock = resultSet.getInt("STOCK");
        String description = resultSet.getString("PRODUCT_DESCRIPCION");
        int state = resultSet.getInt("BTKESTADO");
        Product product = Product.builder().id(id)
                .name(name)
                .brand(brand)
                .price(price)
                .image(image)
                .stock(stock)
                .description(description)
                .state(state).build();

        return product;
    }
}
