package org.zk.entites;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
public class Post implements Serializable {
    private static final long serialVersionUID = 6583871023213001867L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(nullable = false, length = 30)
    private String subject;

    @NotNull
    @Size(min = 5, max = 20000)
    @Column(nullable = false, length = 20000)
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar postDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastModifiedDate;

    @NotNull
    @ManyToOne(optional = false)
    // this is enough for Hibernate schema generation but for EclipseLink next line is needed
    @JoinColumn(nullable = false)
    private Author author;

    @ElementCollection
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("commentDate ASC")
    // TODO try with SortedSet
    private List<PostComment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
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

    public void addComment(@NotNull PostComment comment) {
        if (!comments.contains(comment)) {
            comments.add(comment);
            comment.setPost(this);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post that = (Post) o;

        return Objects.equal(this.subject, that.subject) &&
                Objects.equal(this.body, that.body) &&
                Objects.equal(this.postDate, that.postDate) &&
                Objects.equal(this.createdDate, that.createdDate) &&
                Objects.equal(this.lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subject, body, postDate, createdDate, lastModifiedDate);
    }
}
