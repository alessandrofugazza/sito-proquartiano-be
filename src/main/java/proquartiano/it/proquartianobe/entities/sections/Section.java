package proquartiano.it.proquartianobe.entities.sections;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import proquartiano.it.proquartianobe.entities.articles.Article;
import proquartiano.it.proquartianobe.enums.ESection;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sections")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private ESection name;
    @Column(nullable = false)
    // todo check potential issues of FetchType.EAGER
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section")
    @JsonBackReference
    private List<Article> articles;

    public Section(ESection name) {
        this.name = name;
    }

    public void setName(ESection name) {
        this.name = name;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


}
