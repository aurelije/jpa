package org.zk.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

@Embeddable
public class Point {
    @Min(1)
    private int sizeX;
    @Min(1)
    private int sizeY;

    public Point(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    // private access is against JPA specification but works on both providers
    private Point() {
        // to keep JPA providers happy
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
