package com.p10.Inventory_Management.controller;

import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/article")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/create")
    public InventoryDTO addArticle(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addArticle(inventoryDTO);
    }

    @GetMapping("/listAll")
    public List<InventoryDTO> viewAllArticles() {
        return inventoryService.getAllArticle();
    }

    @GetMapping("/find/{articleId}")
    public ResponseEntity<InventoryDTO> viewArticle(@PathVariable("articleId") Long articleId) {
        return inventoryService.getArticleById(articleId)
                .map(inventoryService::mapToDTO)// Convert Article to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateQuantity/{articleId}")
    public ResponseEntity<InventoryDTO> updateArticle(@PathVariable Long articleId, @RequestParam int quantity) {
        InventoryDTO updatedArticle = inventoryService.updateQuantity(articleId,quantity);
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

    @PutMapping("/assign/{articleId}/{empId}")
    public ResponseEntity<InventoryDTO> assignArticle(@PathVariable Long articleId, @PathVariable Long empId, @RequestParam String issueDate) {
        System.out.println("Received articleId: " + articleId + ", empId: " + empId + ", issueDate: " + issueDate);
        InventoryDTO updatedArticle = inventoryService.assignArticleToEmployee(articleId,empId,LocalDate.parse(issueDate));
        if(updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        }
        return ResponseEntity.notFound().build();
    }
}
