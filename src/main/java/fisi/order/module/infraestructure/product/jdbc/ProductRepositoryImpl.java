package fisi.order.module.infraestructure.product.jdbc;

import fisi.order.module.domain.product.model.Product;
import fisi.order.module.domain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRowMapper row;

    @Override
    public List<Product> productListAvailable() {
        String query = "select CPRODUCT, PRODUCT_NAME, PRODUCT_PRECIO, PRODUCT_IMAGE, MARCA_NAME from TMPRODUCTO U "
                + "INNER JOIN tmmarca M ON M.CMARCA = U.CMARCA";

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(query);
        return row.mapRow(rows);
    }

    @Override
    public List<Product> productListAll() {
        String query = "select CPRODUCT, PRODUCT_NAME, PRODUCT_PRECIO, " +
                "PRODUCT_IMAGE, MARCA_NAME, STOCK, PRODUCT_DESCRIPCION, BTKESTADO from TMPRODUCTO U "
                + "INNER JOIN tmmarca M ON M.CMARCA = U.CMARCA";

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(query);
        return row.mapRowAll(rows);
    }

    @Override
    public Product getProduct(int productId) {
        String query = "select CPRODUCT, PRODUCT_NAME, PRODUCT_PRECIO, " +
                "PRODUCT_IMAGE, MARCA_NAME, STOCK, PRODUCT_DESCRIPCION, BTKESTADO from TMPRODUCTO U "
                + "INNER JOIN tmmarca M ON M.CMARCA = U.CMARCA WHERE CPRODUCT=?";

        return jdbcTemplate.queryForObject(query, new Object[]{productId}, new ProductRowMapper());
    }

    @Override
    public int addProduct(Product product) {
        String insertQuery = "INSERT INTO TMPRODUCTO (PRODUCT_NAME, PRODUCT_PRECIO, CMARCA, STOCK, " +
                "PRODUCT_IMAGE, PRODUCT_DESCRIPCION, BTKESTADO) values (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"CPRODUCT"});

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getBrand());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImage());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getState());

            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int updateProduct(Product product) {
        String updateQuery = "UPDATE TMPRODUCTO SET PRODUCT_NAME = ?, PRODUCT_PRECIO= ?, CMARCA = ?, STOCK = ?, " +
                "PRODUCT_IMAGE = ?, PRODUCT_DESCRIPCION = ?, BTKESTADO = ? WHERE CPRODUCT =?";

        jdbcTemplate.update(updateQuery,
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getStock(),
                product.getImage(),
                product.getDescription(),
                product.getState(),
                product.getId()
        );
        return product.getId();
    }

    @Override
    public int deleteProduct(int idProduct) {
        String deleteQuery = "DELETE FROM TMPRODUCTO WHERE CPRODUCT = ?";
        jdbcTemplate.update(deleteQuery, idProduct);
        return idProduct;
    }
}
