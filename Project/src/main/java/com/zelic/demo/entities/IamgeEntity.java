package com.zelic.demo.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "iamges", schema = "cocktails", catalog = "")
public class IamgeEntity {
    private Integer id;
    private Integer cocId;
    private byte[] content;

    @Id
    @Column(name = "img_id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coc_id", nullable = false)
    public Integer getCocId() {
        return cocId;
    }

    public void setCocId(Integer cocId) {
        this.cocId = cocId;
    }

    @Basic
    @Column(name = "img_contetn", nullable = false)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IamgeEntity that = (IamgeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cocId, that.cocId) &&
                Arrays.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, cocId);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
