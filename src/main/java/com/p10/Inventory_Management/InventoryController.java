package com.p10.Inventory_Management;

import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public Article addArticle(@RequestBody Article article) {
        return inventoryService.addArticle(article);
    }

    @GetMapping("/listAll")
    public List<Article> viewAllArticles() {
        return inventoryService.getAllArticle();
    }

    @GetMapping("/find/{articleId}")
    public ResponseEntity<Article> viewArticle(@PathVariable("articleId") Long articleId) {
        return inventoryService.getArticleById(articleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateQuantity/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long articleId, @RequestParam int quantity) {
        Article updatedArticle = inventoryService.updateQuantity(articleId,quantity);
        if(updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        inventoryService.deleteArticleById(articleId);
        return ResponseEntity.noContent().build();
    }
}
