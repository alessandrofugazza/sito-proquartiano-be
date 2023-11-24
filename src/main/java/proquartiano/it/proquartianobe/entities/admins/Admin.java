package proquartiano.it.proquartianobe.entities.admins;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import proquartiano.it.proquartianobe.entities.articles.Article;
import proquartiano.it.proquartianobe.enums.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "admins")
@Getter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"password"})
public class Admin implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String signature;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    @JsonBackReference
    private List<Article> articles;
    @Enumerated(EnumType.STRING)
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public static class AdminBuilder {
        private Faker fkr = new Faker();
        private String username = fkr.name().username();
        private String email = fkr.internet().emailAddress();
        private List<Article> articles = new ArrayList<>();
    }

}
