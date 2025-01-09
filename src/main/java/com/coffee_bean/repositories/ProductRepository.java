package com.coffee_bean.repositories;

import com.coffee_bean.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    SQL QUERY (Structured Query Language)
//    @NativeQuery(value = "SELECT * FROM products WHERE fk_category_id = ?1")
//    List<Product> findByCategory(Long id);

    //    JPQL QUERY (Java Persistence Query Language)
    @Query("SELECT products FROM Product products WHERE products.category.id = :id")
    List<Product> findByCategory(@Param("id") Long id);
}