package proquartiano.it.proquartianobe.admins;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import proquartiano.it.proquartianobe.articles.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "admins")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    @JsonBackReference
    private List<Article> articles;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class AdminBuilder {
        private Faker fkr = new Faker();
        private String username = fkr.name().username();
        private String email = fkr.internet().emailAddress();
        private List<Article> articles = new ArrayList<>();
    }

}
