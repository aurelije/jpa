package org.zk.entities;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Image implements Serializable {
    private static final long serialVersionUID = -3793347872874713865L;

    @NotNull
    @Size(min = 2, max = 30)
    @Column(nullable = false, length = 30)
    private String title;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(nullable = false)
    private String fileName;

    @Valid
    @NotNull
    @Embedded
    private Point point;

    public Image(String title, String fileName, Point point) {
        this.title = title;
        this.fileName = fileName;
        this.point = this.point;
    }

    private Image() {
        // to keep JPA providers happy
    }

    public String getTitle() {
        return title;
    }

    public String getFileName() {
        return fileName;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image that = (Image) o;

        return com.google.common.base.Objects.equal(this.getTitle(), that.getTitle()) &&
                com.google.common.base.Objects.equal(this.getFileName(), that.getFileName());

    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getTitle(), getFileName());
    }

}
