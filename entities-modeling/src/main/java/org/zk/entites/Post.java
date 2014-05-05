package org.zk.entites;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Post implements Serializable {
    private static final long serialVersionUID = 6583871023213001867L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String subject;

    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar postDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastModifiedDate;

    @ManyToOne
    private Author author;

    @ElementCollection
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
    @OrderBy("commentDate ASC")
    // TODO try with SortedSet
    private List<PostComment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Tag> tags = new HashSet<>();

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Calendar getPostDate() {
        return postDate;
    }

    public void setPostDate(Calendar postDate) {
        this.postDate = postDate;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public List<PostComment> getComments() {
        return Collections.unmodifiableList(this.comments);
    }

    public void addComment(PostComment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public boolean removeComment(PostComment comment) {
        final boolean removed = comments.remove(comment);

        if (removed) {
            comment.setPost(null);
        }

        return removed;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("subject", subject)
                .add("body", body)
                .add("postDate", postDate)
                .add("author", author)
                .add("createdDate", createdDate)
                .add("lastModifiedDate", lastModifiedDate)
                .toString();
    }
}
