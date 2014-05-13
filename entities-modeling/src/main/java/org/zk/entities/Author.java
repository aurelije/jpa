package org.zk.entities;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Author extends BaseEntity {

    private static final long serialVersionUID = -3406541862852082421L;

    @NotNull
    @Pattern(regexp = "^\\w{3,20}$",  message = "org.zk.entities.Author.userPassword.invalidPattern")
    @Column(nullable = false, length = 20)
    private String userName;

    @NotNull
    @Pattern(regexp = "^\\w{6,40}$", message = "org.zk.entities.Author.userPassword.invalidPattern")
    @Column(nullable = false, length = 40)
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author that = (Author) o;

        return Objects.equal(this.userPassword, that.userPassword) &&
                Objects.equal(this.userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userPassword, userName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("userPassword", userPassword)
                .add("userName", userName)
                .add("createdDate", createdDate)
                .add("lastModifiedDate", lastModifiedDate)
                .toString();
    }
}
