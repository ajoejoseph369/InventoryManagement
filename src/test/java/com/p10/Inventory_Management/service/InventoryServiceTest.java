package com.p10.Inventory_Management.service;

import com.p10.Inventory_Management.dao.EmployeeDAO;
import com.p10.Inventory_Management.dao.InventoryDAO;
import com.p10.Inventory_Management.dto.InventoryDTO;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InventoryServiceTest {

    @Mock
    private InventoryDAO inventoryDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private InventoryService inventoryService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddArticle(){
        InventoryDTO inventoryDTO = new InventoryDTO("Laptop",5,"Dell","Wi-Fi",false,null,null,false);
        Article article = new Article();
        article.setArticle("Laptop");
        when(inventoryDAO.save(any(Article.class))).thenReturn(article);

        InventoryDTO result = inventoryService.addArticle(inventoryDTO);
        assertEquals("Laptop",result.getArticle());
    }

    @Test
    public void testUpdateQuantity() {
        Article article = new Article();
        article.setQuantity(5);
        when(inventoryDAO.findById(1L)).thenReturn(Optional.of(article));
        when(inventoryDAO.save(any(Article.class))).thenReturn(article);

        InventoryDTO result = inventoryService.updateQuantity(1L, 10);
        assertNotNull(result,"Expected a non-null InventoryDTO");
        assertEquals(10, result.getQuantity());
    }

    @Test
    public void testDeleteArticle() {
        inventoryService.deleteArticleById(1L);
        verify(inventoryDAO,times(1)).delete(1L);
    }

    @Test
    public void testAssignArticleToEmployee() {
        Article article = new Article();
        article.setAssigned(false);
        Employee employee = new Employee();
        when(inventoryDAO.findById(1L)).thenReturn(Optional.of(article));
        when(inventoryDAO.save(any(Article.class))).thenReturn(article);
        when(employeeDAO.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        InventoryDTO result = inventoryService.assignArticleToEmployee(1L,1L, LocalDate.now());
        assertTrue(result.isAssigned());
    }

}
