package entities;

import java.util.Arrays;
import java.util.HashMap;

/**
 * a Junction knows its neighbours
 */
public class Junction {
    // x,y
    int[] coordinates = new int[2];

    Junction up = null;
    Junction right;

    Junction down;
    Junction left;

    public void Junction(Junction up, Junction right, Junction down, Junction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
    }

    public void setPosition(int x,int y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }

    public void addUp(Junction r) {
        this.up = up;
    }

    public void addRight(Junction r) {
        this.right = right;
    }

    public void addDown(Junction r) {
        this.down = down;
    }

    public void addLeft(Junction r) {
        this.left = left;
    }

    public int getPositionX() {
        return coordinates[0];
    }

    public int getPositionY() {
        return coordinates[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Junction junction = (Junction) o;

        return Arrays.equals(coordinates, junction.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
