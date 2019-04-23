package com.zelic.demo.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.zelic.demo.entities.view.Views;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "cocktails", catalog = "")
@JsonFilter("usersFilter")
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private List<CocktailEntity> cocktails;

    @Id
    @Column(name = "usr_id", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "usr_username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "usr_password", nullable = false, length = 255)
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @OneToMany
    @JoinTable(name = "usr_coc", joinColumns = @JoinColumn(name = "usr_id"), inverseJoinColumns = @JoinColumn(name = "coc_id"))
    public List<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }

    public UserEntity() {
    }

    public UserEntity(UserEntity user){
        setId(user.getId());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setCocktails(user.getCocktails());
    }

    public UserEntity(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
