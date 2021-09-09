package fisi.order.module.infraestructure.brand.jdbc;

import fisi.order.module.domain.brand.model.Brand;
import fisi.order.module.domain.product.model.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BrandRowMapper implements RowMapper<Brand> {
    @Override
    public Brand mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("CMARCA");
        String name = resultSet.getString("MARCA_NAME");

        return Brand.builder().id(id)
                .name(name)
                .build();
    }

    public List<Brand> mapRowAll(List<Map<String, Object>> rows) {
        List<Brand> list = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            int id = Integer.parseInt(row.get("CMARCA").toString());
            String name = row.get("MARCA_NAME").toString();
            list.add(Brand.builder()
                    .id(id)
                    .name(name)
                    .build());
        }
        return list;
    }
}
