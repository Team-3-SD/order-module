package fisi.order.module.infraestructure.brand.jdbc;

import fisi.order.module.domain.brand.model.Brand;
import fisi.order.module.domain.brand.repository.BrandRepository;
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
public class BrandRepositoryImpl implements BrandRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BrandRowMapper row;

    @Override
    public int addBrand(Brand brand) {
        String insertQuery = "INSERT INTO TMMARCA (MARCA_NAME) values ( ? )";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertQuery, new String[]{"CMARCA"});
            ps.setString(1, brand.getName());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int updateBrand(Brand brand) {
        String updateQuery = "UPDATE TMMARCA SET MARCA_NAME = ? WHERE CPRODUCT =?";

        jdbcTemplate.update(updateQuery,
                brand.getName(),
                brand.getId()
        );
        return brand.getId();
    }

    @Override
    public int deleteProduct(int idBrand) {
        String deleteQuery = "DELETE FROM TMMARCA WHERE CMARCA = ?";
        jdbcTemplate.update(deleteQuery, idBrand);
        String deleteProductQuery = "DELETE FROM TMPRODUCTO WHERE CMARCA = ?";
        jdbcTemplate.update(deleteProductQuery, idBrand);
        return idBrand;
    }

    @Override
    public List<Brand> brandList() {
        String query = "SELECT CMARCA, MARCA_NAME FROM TMMARCA ";

        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(query);
        return row.mapRowAll(rows);
    }

    @Override
    public Brand getBrand(int idBrand) {
        String query = "SELECT CMARCA, MARCA_NAME FROM TMMARCA WHERE CMARCA = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{idBrand}, new BrandRowMapper());
    }
}
