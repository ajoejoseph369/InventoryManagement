package com.p10.Inventory_Management.controller;

import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.dto.getUnassignedDTO;
import com.p10.Inventory_Management.dto.listAllDTO;
import com.p10.Inventory_Management.entity.Article;
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
    public List<listAllDTO> viewAllArticles() {
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
//        System.out.println("Received articleId: " + articleId + ", empId: " + empId + ", issueDate: " + issueDate);
        InventoryDTO updatedArticle = inventoryService.assignArticleToEmployee(articleId,empId,LocalDate.parse(issueDate));
        if(updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/markDefective/{articleId}")
    public ResponseEntity<InventoryDTO> markDefective(@PathVariable Long articleId) {
        InventoryDTO updatedArticle = inventoryService.updateCondition(articleId);
        return ResponseEntity.ok(updatedArticle);
    }

    @PutMapping("/updateMake/{articleId}")
    public ResponseEntity<InventoryDTO> updateMake(@PathVariable Long articleId, @RequestParam String make) {
        InventoryDTO updatedArticle = inventoryService.updateMake(articleId,make);
        return ResponseEntity.ok(updatedArticle);
    }

    @GetMapping("/search/{article}")
    public List<Article> searchArticle(@PathVariable String article) {
        return inventoryService.findArticleByArticle((article));
    }

    @GetMapping("/get/unassigned")
    public List<getUnassignedDTO> getUnassigned() {
        return  inventoryService.findArticleByAssigned();
    }

    @DeleteMapping("/deleteArticles")
    public ResponseEntity<Void> deleteMultipleArticles(@RequestParam List<Long> articleIds){
        inventoryService.deleteMultipleArticles(articleIds);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/{article}")
    public Long countArticle(@PathVariable String article) {
        return inventoryService.countArticlesByArticle(article);
    }

    @GetMapping("/findAssignedTo/{empId}")
    public List<Article> findAssignedTo(@PathVariable Long empId) {
        return inventoryService.findArticlesByEmpId(empId);
    }

    @GetMapping("/findByMake/{make}")
    public List<Article> findByMake(@PathVariable String make) {
        return inventoryService.findArticlesByMake(make);
    }

    @GetMapping("/countTotalArticles")
    public Long countTotalArticles() {
        return inventoryService.countTotalArticles();
    }

    @GetMapping("/getAssignedArticlesList")
    public List<Article> getAssignedArticlesList() {
        return inventoryService.findAssignedArticles();
    }

    @PostMapping("/updateAssignment/{articleId}")
    public InventoryDTO updateStatus(@PathVariable Long articleId, @RequestParam boolean assignment) {
        return inventoryService.updateStatus(articleId,assignment);
    }
}
