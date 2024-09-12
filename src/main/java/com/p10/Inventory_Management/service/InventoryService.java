package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Article addArticle(Article article){
        return inventoryRepository.save(article);
    }

    public List<Article> getAllArticle(){
        return inventoryRepository.findAll();
    }

    public Optional<Article> getArticleById(Long articleId){
        return inventoryRepository.findById(articleId);
    }

    public Article updateQuantity(Long articleId, int quantity){
        Optional<Article> article = getArticleById(articleId);
        if(article.isPresent()){
            article.get().setQuantity(quantity);
            return inventoryRepository.save(article.get());
        }
        return null;
    }


    public Optional<Article> deleteArticleById(Long articleId){
        Optional<Article> article = getArticleById(articleId);
        if(article.isPresent()){
            inventoryRepository.deleteById(articleId);
        }
        return Optional.empty();
    }
}
