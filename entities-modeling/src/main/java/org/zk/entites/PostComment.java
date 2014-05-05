package org.zk.entites;

import com.google.common.base.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class PostComment implements Serializable {
    private static final long serialVersionUID = 5478374318852831308L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    @Size(min = 2, max = 2000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar commentDate;

    @ManyToOne
    private Post post;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Calendar commentDate) {
        this.commentDate = commentDate;
    }

    public Post getPost() {
        return post;
    }

    void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("text", content)
                .add("post", post)
                .add("commentDate", commentDate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostComment that = (PostComment) o;

        return Objects.equal(this.content, that.content) &&
                Objects.equal(this.commentDate, that.commentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content, commentDate);
    }
}
