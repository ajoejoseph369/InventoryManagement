package com.p10.Inventory_Management.repository;
import com.p10.Inventory_Management.entity.Article;
import com.p10.Inventory_Management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface InventoryRepository extends JpaRepository<Article, Long> {
    @Query("SELECT i FROM Article i WHERE i.article = :article")
    List<Article> findArticleByArticle(@Param("article") String article);

    @Query("SELECT i FROM Article i WHERE i.assigned = false")
    List<Article> findArticleByAssigned();

    @Modifying
    @Query("DELETE FROM Article i WHERE i.articleId IN :articleIds")
    void deleteArticleByArticleIds(@Param("articleIds") List<Long> articleIds);

    @Query("SELECT COUNT(i) FROM Article i WHERE i.article = :article")
    Long countArticleByArticle(@Param("article") String article);

    @Query("SELECT a FROM Article a WHERE a.assignedTo.empId = :empId")
    List<Article> findArticlesByEmployeeId(@Param("empId") Long empId);

}
