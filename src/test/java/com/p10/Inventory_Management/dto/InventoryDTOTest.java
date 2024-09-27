package com.p10.Inventory_Management.dto;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class InventoryDTOTest {

    @Test
    public void testDTOCreation(){
        InventoryDTO inventoryDTO = new InventoryDTO("Laptop",5,"Dell","Wi-Fi",false,null,null,false);
        Assertions.assertEquals("Laptop",inventoryDTO.getArticle());
        assertEquals("Laptop",inventoryDTO.getArticle());
    }

    @Test
    public void testSetters(){
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setArticle("Laptop");
        inventoryDTO.setQuantity(5);
        assertEquals("Laptop",inventoryDTO.getArticle());
        assertEquals(5,inventoryDTO.getQuantity());
    }
}
