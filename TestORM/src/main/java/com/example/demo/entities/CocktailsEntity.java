package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cocktails", schema = "cocktails", catalog = "")
public class CocktailsEntity {
    private int cocId;
    private String cocName;
    @JsonIgnore
    private List<UsersEntity> users;

    @Id
    @Column(name = "coc_id", nullable = false)
    public int getCocId() {
        return cocId;
    }

    public void setCocId(int cocId) {
        this.cocId = cocId;
    }

    @Basic
    @Column(name = "coc_name", nullable = false, length = 255)
    public String getCocName() {
        return cocName;
    }

    public void setCocName(String cocName) {
        this.cocName = cocName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailsEntity that = (CocktailsEntity) o;
        return cocId == that.cocId &&
                Objects.equals(cocName, that.cocName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocId, cocName);
    }

    @ManyToMany(mappedBy = "cocktails", fetch = FetchType.LAZY)
    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }
}
