package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.dao.EmployeeDAO;
import com.p10.Inventory_Management.dao.InventoryDAO;
import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.dto.getUnassignedDTO;
import com.p10.Inventory_Management.dto.listAllDTO;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.entity.Employee;
import com.p10.Inventory_Management.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    public List<listAllDTO> getAllArticle(){
        List<Article> articles = inventoryDAO.findAll();
        return articles.stream()// Convert the list to a stream
                .map(this::mapToListAllDTO) // Convert each Article to InventoryDTO
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
        else {
            return null;
        }
    }

    public void deleteArticleById(Long articleId){
        inventoryDAO.delete(articleId);
    }

    public InventoryDTO mapToDTO(Article article){
        return new InventoryDTO(article.getArticle(), article.getQuantity(), article.getMake(), article.getConnectionType(), article.isAssigned(), article.getAssignedTo(), article.getDateOfIssue(), article.getDefective() );
    }

    public listAllDTO mapToListAllDTO(Article article){
        return new listAllDTO(article.getArticleId(), article.getArticle(), article.getMake());
    }

    public getUnassignedDTO mapToGetUnassignedDTO(Article article){
        return new getUnassignedDTO(article.getArticleId(),article.getArticle(),article.getQuantity(),article.getMake(), article.getConnectionType());
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

    public List<getUnassignedDTO> findArticleByAssigned(){
        List<Article> articles = inventoryDAO.findByAssigned();
        return articles.stream()
                .map(this::mapToGetUnassignedDTO)
                .collect(Collectors.toList());
//        return inventoryDAO.findByAssigned();
    }

//    public List<listAllDTO> getAllArticle(){
//        List<Article> articles = inventoryDAO.findAll();
//        return articles.stream()// Convert the list to a stream
//                .map(this::mapToListAllDTO) // Convert each Article to InventoryDTO
//                .collect(Collectors.toList()); // Collect the transformed DTOs into a List
//    }

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

    public List<Article> findArticlesByMake(String make){
        return inventoryDAO.findArticlesByMake(make);
    }

    public Long countTotalArticles(){
        return inventoryDAO.countTotalArticles();
    }

    public List<Article> findAssignedArticles(){
        return inventoryDAO.findAssignedArticles();
    }

    public InventoryDTO updateStatus(Long articleId, boolean status){
        Optional<Article> article = inventoryDAO.findById(articleId);
        if(article.isPresent()){
            article.get().setAssigned(status);
            Article savedArticle = inventoryDAO.save(article.get());
            return mapToDTO(savedArticle);
        }
        return null;
    }
}

