package proquartiano.it.proquartianobe.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import proquartiano.it.proquartianobe.article.Article;

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
    @Column( nullable = false )
    private String name;
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Article> articles;

    public static class CategoryBuilder {
        Faker fkr = new Faker();
        private String name = fkr.book().genre();
        private List<Article> articles = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
