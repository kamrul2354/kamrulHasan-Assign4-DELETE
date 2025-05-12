package ca.sheridan.hasankam.dao;

import ca.sheridan.hasankam.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsDatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    public long addProduct(Product product) {
        String insert = "INSERT INTO product (productCode, productBrand, quantityInHand, unitPrice) VALUES (:productCode, :productBrand, :quantityInHand, :unitPrice)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("productCode", product.getProductCode())
            .addValue("productBrand", product.getProductBrand())
            .addValue("quantityInHand", product.getQuantityInHand())
            .addValue("unitPrice", product.getUnitPrice());
        return jdbc.update(insert, namedParameters);
    }

    public List<Product> selectProducts() {
        String query = "SELECT * FROM product";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getProductById(int id) {
        String query = "SELECT * FROM product WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        List<Product> products = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Product.class));
        return products.isEmpty() ? null : products.get(0);
    }

    public int updateProduct(Product product) {
        String update = "UPDATE product SET productCode = :productCode, productBrand = :productBrand, quantityInHand = :quantityInHand, unitPrice = :unitPrice WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("productCode", product.getProductCode())
            .addValue("productBrand", product.getProductBrand())
            .addValue("quantityInHand", product.getQuantityInHand())
            .addValue("unitPrice", product.getUnitPrice())
            .addValue("id", product.getId());
        return jdbc.update(update, namedParameters);
    }

    public int deleteProduct(int id) {
        String delete = "DELETE FROM product WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return jdbc.update(delete, namedParameters);
    }

    public List<Product> selectProductsByBrand(String productBrand) {
        String query = "SELECT * FROM product WHERE productBrand = :productBrand";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("productBrand", productBrand);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> selectProductsByQuantity(int quantityInHand) {
        String query = "SELECT * FROM product WHERE quantityInHand < :quantityInHand";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("quantityInHand", quantityInHand);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Product.class));
    }
}
