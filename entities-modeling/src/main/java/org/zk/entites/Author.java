package org.zk.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = -3406541862852082421L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userPassword;
    private String userName;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastModifiedDate;
    @OneToMany(mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
