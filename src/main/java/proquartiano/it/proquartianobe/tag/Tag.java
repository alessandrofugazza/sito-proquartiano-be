package proquartiano.it.proquartianobe.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import proquartiano.it.proquartianobe.article.Article;

import java.util.*;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Tag {
    @Id
    @GeneratedValue
    private UUID id;
    @Column( nullable = false )
    private String name;
    @ManyToMany(mappedBy = "tags")
    @JsonBackReference
    private List<Article> articles;

    public static class TagBuilder {
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
