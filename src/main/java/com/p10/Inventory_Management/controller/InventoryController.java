package com.p10.Inventory_Management.controller;

import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.dto.getUnassignedDTO;
import com.p10.Inventory_Management.dto.listAllDTO;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Add article", description = "Add a new article in the inventory")
    public InventoryDTO addArticle(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addArticle(inventoryDTO);
    }

    @GetMapping("/listAll")
    @Operation(summary = "Get all articles", description = "Retrieve a list of all articles in the inventory")
    public List<listAllDTO> viewAllArticles() {
        return inventoryService.getAllArticle();
    }

    @GetMapping("/find/{articleId}")
    @Operation(summary = "Find article", description = "Find article details using article ID")
    public ResponseEntity<InventoryDTO> viewArticle(@PathVariable("articleId") Long articleId) {
        return inventoryService.getArticleById(articleId)
                .map(inventoryService::mapToDTO)// Convert Article to DTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateQuantity/{articleId}")
    @Operation(summary = "Update quantity", description = "Update quantity of an article given its article ID")
    public ResponseEntity<InventoryDTO> updateArticle(@PathVariable Long articleId, @RequestParam int quantity) {
        InventoryDTO updatedArticle = inventoryService.updateQuantity(articleId,quantity);
        if(updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{articleId}")
    @Operation(summary = "Delete Article", description = "Deletes an article from the inventory given its article ID")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        inventoryService.deleteArticleById(articleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/assign/{articleId}/{empId}")
    @Operation(summary = "Assign Article", description = "Assign an article to an employee given article ID and employee ID")
    public ResponseEntity<InventoryDTO> assignArticle(@PathVariable Long articleId, @PathVariable Long empId, @RequestParam String issueDate) {
//        System.out.println("Received articleId: " + articleId + ", empId: " + empId + ", issueDate: " + issueDate);
        InventoryDTO updatedArticle = inventoryService.assignArticleToEmployee(articleId,empId,LocalDate.parse(issueDate));
        if(updatedArticle != null) {
            return ResponseEntity.ok(updatedArticle);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/markDefective/{articleId}")
    @Operation(summary = "Mark article defective", description = "Mark an article as defective given its article ID")
    public ResponseEntity<InventoryDTO> markDefective(@PathVariable Long articleId) {
        InventoryDTO updatedArticle = inventoryService.updateCondition(articleId);
        return ResponseEntity.ok(updatedArticle);
    }

    @PutMapping("/updateMake/{articleId}")
    @Operation(summary = "Get all articles", description = "Retrieve a list of all articles in the inventory")
    public ResponseEntity<InventoryDTO> updateMake(@PathVariable Long articleId, @RequestParam String make) {
        InventoryDTO updatedArticle = inventoryService.updateMake(articleId,make);
        return ResponseEntity.ok(updatedArticle);
    }

    @GetMapping("/search/{article}")
    @Operation(summary = "Search article details", description = "Retrieve details of articles in the inventory")
    public List<Article> searchArticle(@PathVariable String article) {
        return inventoryService.findArticleByArticle((article));
    }

    @GetMapping("/get/unassigned")
    @Operation(summary = "Find unassigned articles", description = "Retrieve a list of all unassigned articles in the inventory")
    public List<getUnassignedDTO> getUnassigned() {
        return  inventoryService.findArticleByAssigned();
    }

    @DeleteMapping("/deleteArticles")
    @Operation(summary = "Delete multiple articles", description = "Delete multiple articles from the inventory")
    public ResponseEntity<Void> deleteMultipleArticles(@RequestParam List<Long> articleIds){
        inventoryService.deleteMultipleArticles(articleIds);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/{article}")
    @Operation(summary = "Get count an article", description = "Get count of an article in the inventory given its article name")
    public Long countArticle(@PathVariable String article) {
        return inventoryService.countArticlesByArticle(article);
    }

    @GetMapping("/findAssignedTo/{empId}")
    @Operation(summary = "Find assigned articles to an employee", description = "Retrieve a list of all articles assigned to an employee given the employee ID")
    public List<Article> findAssignedTo(@PathVariable Long empId) {
        return inventoryService.findArticlesByEmpId(empId);
    }

    @GetMapping("/findByMake/{make}")
    @Operation(summary = "Get all articles", description = "Retrieve a list of all articles in the inventory")
    public List<Article> findByMake(@PathVariable String make) {
        return inventoryService.findArticlesByMake(make);
    }

    @GetMapping("/countTotalArticles")
    @Operation(summary = "Get total number of articles", description = "Retrieve total count of articles in the inventory")
    public Long countTotalArticles() {
        return inventoryService.countTotalArticles();
    }

    @GetMapping("/getAssignedArticlesList")
    @Operation(summary = "Get assigned articles", description = "Retrieve a list of all assigned articles in the inventory")
    public List<Article> getAssignedArticlesList() {
        return inventoryService.findAssignedArticles();
    }

    @PostMapping("/updateAssignment/{articleId}")
    @Operation(summary = "Update assignment", description = "Update assignment status of an article given its article ID")
    public InventoryDTO updateStatus(@PathVariable Long articleId, @RequestParam boolean assignment) {
        return inventoryService.updateStatus(articleId,assignment);
    }
}
