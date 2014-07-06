package org.zk.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POST_COMMENT")
public class PostComment extends BaseEntity {
    private static final long serialVersionUID = 5478374318852831308L;

    @NotNull
    @Size(min = 2, max = 2000)
    @Column(nullable = false, length = 2000)
    private String content;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Post post;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostComment)) return false;

        PostComment that = (PostComment) o;

        return Objects.equal(this.getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getContent());
    }
}
