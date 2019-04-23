package com.zelic.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.zelic.demo.entities.view.Views;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cocktails", schema = "cocktails", catalog = "")
public class CocktailEntity {
    @JsonView({Views.Public.class})
    private Integer id;
    @JsonView({Views.Public.class})
    private String name;
    @JsonIgnore
    private List<IamgeEntity> images;
    @JsonIgnore
    private List<IngredientEntity> ingredients;

    @Id
    @Column(name = "coc_id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coc_name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailEntity that = (CocktailEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany
    public List<IamgeEntity> getImages() {
        return images;
    }

    public void setImages(List<IamgeEntity> images) {
        this.images = images;
    }

    @ManyToMany
    @JoinTable(name = "coc_ing", catalog = "", schema = "cocktails", joinColumns = @JoinColumn(name = "coc_id", referencedColumnName = "coc_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "ing_id", referencedColumnName = "ing_id", nullable = false))
    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public CocktailEntity() {
    }

    public CocktailEntity(Integer id) {
        this.id = id;
    }
}
