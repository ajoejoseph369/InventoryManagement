package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.dao.EmployeeDAO;
import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.dao.InventoryDAO;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.entity.Employee;
import com.p10.Inventory_Management.repository.EmployeeRepository;
import com.p10.Inventory_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryDAO inventoryDAO;

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private InventoryRepository inventoryRepository;

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
        return new InventoryDTO(article.getArticle(), article.getQuantity(), article.getMake(), article.getConnectionType(), article.isAssigned(), article.getAssignedTo(), article.getDateOfIssue(), article.getDefective() );
    }

    private Article mapToEntity(InventoryDTO inventoryDTO){
        Article article = new Article();
//        article.setArticleId(inventoryDTO.getArticleId());
        article.setArticle(inventoryDTO.getArticle());
        article.setQuantity(inventoryDTO.getQuantity());
        article.setMake(inventoryDTO.getMake());
        article.setConnectionType(inventoryDTO.getConnectionType());
        article.setAssigned(inventoryDTO.isAssigned());
        article.setAssignedTo(inventoryDTO.getAssignedTo());
        article.setDateOfIssue(inventoryDTO.getDateOfIssue());
        article.setDefective(inventoryDTO.getDefective());
        return article;
    }

    public InventoryDTO assignArticleToEmployee(Long articleId, Long employeeId, LocalDate issueDate){
        Optional<Article> article = inventoryDAO.findById(articleId);
        Optional<Employee> employee = employeeDAO.getEmployeeById(employeeId);

        if(article.isPresent() && employee.isPresent()){
            Article a = article.get();
            a.setAssigned(true);
            a.setAssignedTo(employee.get());
            a.setDateOfIssue(issueDate);
            Article savedArticle = inventoryDAO.save(a);
            return mapToDTO(savedArticle);
        }
        return null;
    }

    public InventoryDTO updateCondition(Long articleId){
        Optional<Article> article = inventoryDAO.findById(articleId);
        if(article.isPresent()){
            article.get().setDefective(true);
            Article updatedArticle = inventoryDAO.save(article.get());
            return mapToDTO(updatedArticle);
        }
        return null;
    }

    public InventoryDTO updateMake(Long articleId, String make){
        Optional<Article> article = inventoryDAO.findById(articleId);
        if(article.isPresent()){
            article.get().setMake(make);
            Article savedArticle = inventoryDAO.save(article.get());
            return mapToDTO(savedArticle);
        }
        return null;
    }

    public List<Article> findArticleByArticle(String articleName){
        return inventoryDAO.findByName(articleName);
    }

    public List<Article> findArticleByAssigned(){
        return inventoryDAO.findByAssigned();
    }

    @Transactional
    public void deleteMultipleArticles(List<Long> articleIds){
        inventoryDAO.deleteMultipleArticles(articleIds);
    }

    public Long countArticlesByArticle(String articleName){
        return inventoryDAO.countArticleByArticle(articleName);
    }

    public List<Article> findArticlesByEmpId(Long empId){
        return inventoryDAO.findArticleByEmpId(empId);
    }
}

