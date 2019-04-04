package com.evilbird.warcraft.item.layer.common;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;

public class BooleanMatrix
{
    private boolean[][] values;

    public BooleanMatrix(int dimension) {
        values = new boolean[dimension][dimension];
    }
    
    public Collection<BooleanMatrix> children(int dimension) {
        return null;
    }

    public boolean matches(BooleanMatrix another) {
        return false;
    }

    public boolean get(int x, int y) {
        return values[x][y];
    }

    public boolean get(GridPoint2 point) {
        return values[point.x][point.y];
    }

    public void set(int x, int y, boolean value) {
        values[x][y] = value;
    }

    public void set(GridPoint2 point, boolean value) {
        values[point.x][point.y] = value;
    }
}
