package proquartiano.it.proquartianobe.entities.categories;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import proquartiano.it.proquartianobe.entities.articles.Article;
import proquartiano.it.proquartianobe.enums.ESection;

import java.util.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Article> articles;

    public Category(String name) {
        this.name = name;
    }

//    public static class CategoryBuilder {
//        Faker fkr = new Faker();
//        private String name = fkr.book().genre();
//        private List<Article> articles = new ArrayList<>();
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
