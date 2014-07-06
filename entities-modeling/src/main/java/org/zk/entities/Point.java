package org.zk.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;

/**
 * Created by zkadragic on 7/6/14.
 */
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
