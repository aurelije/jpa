package org.zk.entities;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tag extends BaseEntity {
    private static final long serialVersionUID = 7464310525684640936L;

    @NotNull
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag that = (Tag) o;

        return Objects.equal(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

}
