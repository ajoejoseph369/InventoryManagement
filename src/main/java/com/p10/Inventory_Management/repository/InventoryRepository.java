package com.p10.Inventory_Management.repository;
import com.p10.Inventory_Management.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Article, Long> {
}
