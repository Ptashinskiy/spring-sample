package com.pptasinskij.web.domain.entities.user;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
public class User {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User newUser(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        return new User(username, password);
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password);
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
