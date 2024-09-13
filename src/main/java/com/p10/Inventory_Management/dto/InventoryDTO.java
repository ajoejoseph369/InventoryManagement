package com.p10.Inventory_Management.dto;

public class InventoryDTO {
//    private Long articleId;
    private String article;
    private int quantity;
    private String make;
    private String connectionType;

    public InventoryDTO() {}

    public InventoryDTO(String article, int quantity, String make, String connectionType) {
//        this.articleId = articleId;
        this.article = article;
        this.quantity = quantity;
        this.make = make;
        this.connectionType = connectionType;
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
}
