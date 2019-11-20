/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.evilbird.engine.common.collection.CollectionUtils;
import com.evilbird.engine.common.error.SizeExceededException;
import com.evilbird.engine.common.lang.Alignment;
import com.evilbird.engine.object.GameObject;

import java.util.Collection;

/**
 * Represents a user interface control that displays a given set of
 * {@link GameObject Items} in a grid pattern. The number of rows and columns can
 * be specified as well as the spacing between and around each cell.
 *
 * @author Blair Butterworth
 */
public class Grid extends Table
{
    private int columnCount;
    private int itemMaximum;
    private int cellSpacing;
    private int cellPaddingVertical;
    private int cellPaddingHorizontal;
    private int cellWidthMinimum;
    private int cellHeightMinimum;

    public Grid(int columnCount, int rowCount) {
        this.columnCount = columnCount;
        this.itemMaximum = columnCount * rowCount;
        setAlignment(Alignment.TopLeft);
    }

    @Override
    public Cell<Actor> add(Actor actor) {
        validateSize(1);
        Cell<Actor> cell = super.add(actor);
        setCellProperties(cell);
        return cell;
    }

    @Override
    public Cell<Actor> add(GameObject gameObject) {
        validateSize(1);
        Cell<Actor> cell = super.add(gameObject);
        setCellProperties(cell);
        return cell;
    }

    public void addObjects(Collection<GameObject> newGameObjects) {
        validateSize(newGameObjects.size());
        CollectionUtils.forEach(newGameObjects, this::add);
    }

    public void setCellPadding(int cellPadding) {
        this.cellPaddingVertical = cellPadding;
        this.cellPaddingHorizontal = cellPadding;
    }

    public void setCellSpacing(int cellSpacing) {
        this.cellSpacing = cellSpacing;
    }

    public void setCellHeight(int cellHeightMinimum) {
        this.cellHeightMinimum = cellHeightMinimum;
    }

    public void setCellWidth(int cellWidthMinimum) {
        this.cellWidthMinimum = cellWidthMinimum;
    }

    private void setCellProperties(Cell<Actor> cell) {
        int count = control.getCells().size;
        int column = count % columnCount;
        int row = count / columnCount;

        updateCellSize(cell);
        updateCellPadding(cell, row, column);
        updateCellRow(cell, count, column);
    }

    private void updateCellSize(Cell<Actor> cell) {
        if (cellWidthMinimum != 0) {
            cell.width(cellWidthMinimum);
        }
        if (cellHeightMinimum != 0) {
            cell.height(cellHeightMinimum);
        }
    }

    private void updateCellPadding(Cell<Actor> cell, int row, int column) {
        cell.padLeft(cellPaddingHorizontal);
        cell.padRight(cellPaddingHorizontal);

        cell.padTop(cellPaddingVertical);
        cell.padBottom(cellPaddingVertical);

        if (column != 0) {
            cell.padRight(cell.getPadRight() + cellSpacing);
        }
        if (row != 0) {
            cell.padBottom(cell.getPadBottom() + cellSpacing);
        }
    }

    private void updateCellRow(Cell<Actor> cell, int count, int column) {
        if (column == 0) {
            cell.row();
        }
    }

    private void validateSize(int additionalItems) {
        if (objects.size() + additionalItems > itemMaximum) {
            throw new SizeExceededException("Maximum cell count exceeded: " + itemMaximum);
        }
    }
}
