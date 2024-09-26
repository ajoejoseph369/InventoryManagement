package com.p10.Inventory_Management.dto;

public class listAllDTO {
    private Long articleId;
    private String article;
    private String make;

    public listAllDTO() {}

    public listAllDTO(Long articleId, String article, String make) {
        this.articleId = articleId;
        this.article = article;
        this.make = make;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
