package org.zk.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Post extends BaseEntity {
    private static final long serialVersionUID = 6583871023213001867L;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(nullable = false, length = 30)
    private String subject;

    @NotNull
    @Size(min = 5, max = 20000)
    @Column(nullable = false, length = 20000)
    private String body;

    @NotNull
    @ManyToOne(optional = false)
    // this is enough for Hibernate schema generation but for EclipseLink next line is needed
    @JoinColumn(nullable = false)
    private Author author;

    @ElementCollection
    @Valid
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate ASC")
    // TODO try with SortedSet
    private List<PostComment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Tag> tags = new HashSet<>();

    public Set<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Post)) {
            return false;
        }

        Post that = (Post) o;

        return Objects.equal(this.getSubject(), that.getSubject()) &&
                Objects.equal(this.getBody(), that.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSubject(), getBody());
    }
}
