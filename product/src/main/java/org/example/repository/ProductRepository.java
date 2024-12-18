package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByStatus(String status);

    @Query("SELECT SUM(p.value) FROM Product p WHERE p.status = 'Sellable'")
    Double sumValueByStatus();
}
