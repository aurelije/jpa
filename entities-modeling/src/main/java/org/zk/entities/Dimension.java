package org.zk.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
@Access(AccessType.FIELD)
public class Dimension {
    @Min(1)
    private int sizeX;
    @Min(1)
    private int sizeY;

    public Dimension(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    // private access is against JPA specification but works on both providers
    private Dimension() {
        // to keep JPA providers happy
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
        if (!(o instanceof Dimension)) return false;

        Dimension that = (Dimension) o;

        return com.google.common.base.Objects.equal(this.getSizeX(), that.getSizeX()) &&
                com.google.common.base.Objects.equal(this.getSizeY(), that.getSizeY());

    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getSizeX(), getSizeY());
    }
}
