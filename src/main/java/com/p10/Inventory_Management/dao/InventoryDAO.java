package com.p10.Inventory_Management.dao;

import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventoryDAO {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Article save(Article article) {
        return inventoryRepository.save(article);
    }

    public List<Article> findAll() {
        return inventoryRepository.findAll();
    }

    public Optional<Article> findById(Long articleId) {
        return inventoryRepository.findById(articleId);
    }

    public void delete(Long articleId) {
        inventoryRepository.deleteById(articleId);
    }

}
