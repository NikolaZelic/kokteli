package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "cocktails", catalog = "")
public class UsersEntity {
    private int usrId;
    private String usrUsername;
    private String usrPassword;
    @JsonIgnore
    private List<CocktailsEntity> cocktails;

    @Id
    @Column(name = "usr_id", nullable = false)
    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    @Basic
    @Column(name = "usr_username", nullable = false, length = 255)
    public String getUsrUsername() {
        return usrUsername;
    }

    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    @Basic
    @Column(name = "usr_password", nullable = false, length = 255)
    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return usrId == that.usrId &&
                Objects.equals(usrUsername, that.usrUsername) &&
                Objects.equals(usrPassword, that.usrPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usrId, usrUsername, usrPassword);
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "usr_coc", catalog = "", schema = "cocktails", joinColumns = @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "coc_id", referencedColumnName = "coc_id", nullable = false))
    public List<CocktailsEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailsEntity> cocktails) {
        this.cocktails = cocktails;
    }
}
