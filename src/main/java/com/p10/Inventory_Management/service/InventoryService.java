package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.dao.InventoryDAO;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryDAO inventoryDAO;

    public InventoryDTO addArticle(InventoryDTO inventoryDTO){
        Article article = mapToEntity(inventoryDTO);
        Article savedArticle = inventoryDAO.save(article);
        return mapToDTO(savedArticle);
    }

    public List<InventoryDTO> getAllArticle(){
        List<Article> articles = inventoryDAO.findAll();
        return articles.stream()// Convert the list to a stream
                .map(this::mapToDTO) // Convert each Article to InventoryDTO
                .collect(Collectors.toList()); // Collect the transformed DTOs into a List
    }

    public Optional<Article> getArticleById(Long articleId){
        return inventoryDAO.findById(articleId);
    }

    public InventoryDTO updateQuantity(Long articleId, int quantity){
        Optional<Article> article = inventoryDAO.findById(articleId);
        if(article.isPresent()){
            article.get().setQuantity(quantity);
            Article updatedArticle = inventoryDAO.save(article.get());
            return mapToDTO(updatedArticle);
        }
        return null;
    }

    public void deleteArticleById(Long articleId){
        inventoryDAO.delete(articleId);
    }

    public InventoryDTO mapToDTO(Article article){
        return new InventoryDTO(article.getArticle(), article.getQuantity(), article.getMake(), article.getConnectionType());
    }

    private Article mapToEntity(InventoryDTO inventoryDTO){
        Article article = new Article();
//        article.setArticleId(inventoryDTO.getArticleId());
        article.setArticle(inventoryDTO.getArticle());
        article.setQuantity(inventoryDTO.getQuantity());
        article.setMake(inventoryDTO.getMake());
        article.setConnectionType(inventoryDTO.getConnectionType());
        return article;
    }
}

