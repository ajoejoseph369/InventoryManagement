package com.p10.Inventory_Management.dto;

import com.p10.Inventory_Management.entity.Employee;

import java.time.LocalDate;

public class InventoryDTO {
//    private Long articleId;
    private String article;
    private int quantity;
    private String make;
    private String connectionType;
    private boolean assigned;
    private Employee assignedTo;
    private LocalDate dateOfIssue;

    public InventoryDTO() {}

    public InventoryDTO(String article, int quantity, String make, String connectionType, boolean assigned, Employee assignedTo, LocalDate dateOfIssue) {
//        this.articleId = articleId;
        this.article = article;
        this.quantity = quantity;
        this.make = make;
        this.connectionType = connectionType;
        this.assigned = assigned;
        this.assignedTo = assignedTo;
        this.dateOfIssue = dateOfIssue;
    }

//    public Long getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(Long articleId) {
//        this.articleId = articleId;
//    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
