package proquartiano.it.proquartianobe.article;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import proquartiano.it.proquartianobe.admin.Admin;
import proquartiano.it.proquartianobe.category.Category;
import proquartiano.it.proquartianobe.tag.Tag;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "articles")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue
    private UUID id;
    @Column( nullable = false )
    private String title;
    @ManyToOne()
    @JoinColumn(name="author_id", nullable = false)
    @JsonManagedReference
    private Admin author;
    @Column( nullable = false )
    @CreationTimestamp
    private LocalDate date;
    @Column( nullable = false )
    private String content;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "articles_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonManagedReference
    private List<Category> categories;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "articles_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    @JsonManagedReference
    private List<Tag> tags;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Admin author) {
        this.author = author;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class ArticleBuilder {
        private Faker fkr = new Faker();
        private String title = fkr.esports().event();
        private Admin author = Admin.builder().build();
        private LocalDate date = fkr.date().past(365, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        private String content = fkr.book().title();
        private List<Category> categories = new ArrayList<>();
        private List<Tag> tags = new ArrayList<>();

    }
}
