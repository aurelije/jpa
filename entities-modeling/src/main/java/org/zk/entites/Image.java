package org.zk.entites;

import com.google.common.base.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Image implements Serializable {
    private static final long serialVersionUID = -3793347872874713865L;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String file­name;

    private int sizeX;

    private int sizeY;

    public Image(String title, String file­name, int sizeX, int sizeY) {
        this.title = title;
        this.file­name = file­name;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    protected Image() {
        // to keep JPA providers happy
    }

    public String getTitle() {
        return title;
    }

    public String getFile­name() {
        return file­name;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image that = (Image) o;

        return com.google.common.base.Objects.equal(this.title, that.title) &&
                com.google.common.base.Objects.equal(this.file­name, that.file­name) &&
                com.google.common.base.Objects.equal(this.sizeX, that.sizeX) &&
                com.google.common.base.Objects.equal(this.sizeY, that.sizeY);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(title, file­name, sizeX, sizeY);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("title", title)
                .add("file­name", file­name)
                .add("sizeX", sizeX)
                .add("sizeY", sizeY)
                .toString();
    }
}
