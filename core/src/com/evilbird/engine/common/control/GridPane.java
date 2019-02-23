/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import java.util.Collection;

//TODO: Update cell positions when padding, spacings or bounds are altered.
public class GridPane extends ItemGroup
{
    private Drawable background;
    private Item[][] cells;
    private int cellSpacing;
    private int cellPaddingVertical;
    private int cellPaddingHorizontal;
    private int[] cellWidths;
    private int[] cellHeights;
    private int cellWidthMinimum;
    private int cellHeightMinimum;
    private int columnCount;
    private int rowCount;

    public GridPane(int columnCount, int rowCount)
    {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.cells = new Item[columnCount][rowCount];
        this.cellWidths = new int[columnCount];
        this.cellHeights = new int[rowCount];
        this.cellPaddingVertical = 0;
        this.cellPaddingHorizontal = 0;
        this.cellSpacing = 0;
        this.cellWidthMinimum = 0;
        this.cellHeightMinimum = 0;
        this.background = null;
    }

    public Item getCell(int x, int y)
    {
        return cells[x][y];
    }

    public void setBackground(Drawable background)
    {
        this.background = background;
    }

    public void setCellPadding(int cellPadding)
    {
        this.cellPaddingVertical = cellPadding;
        this.cellPaddingHorizontal = cellPadding;
    }

    public void setVerticalCellPadding(int cellPadding)
    {
        this.cellPaddingVertical = cellPadding;
    }

    public void setHorizontalCellPadding(int cellPadding)
    {
        this.cellPaddingHorizontal = cellPadding;
    }

    public void setCellSpacing(int cellSpacing)
    {
        this.cellSpacing = cellSpacing;
    }

    public void setCellHeightMinimum(int cellHeightMinimum)
    {
        this.cellHeightMinimum = cellHeightMinimum;
        for (int row = 0; row < rowCount; row++){
            cellHeights[row] = Math.max(cellHeights[row], cellHeightMinimum);
        }
    }

    public void setCellWidthMinimum(int cellWidthMinimum)
    {
        this.cellWidthMinimum = cellWidthMinimum;
        for (int column = 0; column < columnCount; column++){
            cellWidths[column] = Math.max(cellWidths[column], cellWidthMinimum);
        }
    }

    public void setCells(Collection<Item> addCells)
    {
        int x = 0, y = 0;
        for (Item cell: addCells){
            if (y < rowCount){
                setCell(cell, x, y);
                y = x != columnCount - 1 ? y : y + 1;
                x = x == columnCount - 1 ? 0 : x + 1;
            }
        }
    }

    public void setCell(Item cell, int column, int row)
    {
        addItem(cell);
        cells[column][row] = cell;
        updateCellWidths(column);
        updateCellHeights(row);
        updateCellPositions();
    }

    private void updateCellWidths(int column)
    {
        for (int row = 0; row < rowCount; row++){
            Item cell = cells[column][row];
            if (cell != null) {
                cellWidths[column] = Math.max(cellWidths[column], (int)cell.getWidth());
            }
        }
    }

    private void updateCellHeights(int row)
    {
        for (int column = 0; column < columnCount; column++){
            Item cell = cells[column][row];
            if (cell != null) {
                cellHeights[row] = Math.max(cellHeights[row], (int)cell.getHeight());
            }
        }
    }

    private void updateCellPositions()
    {
        int cumulativeX;
        int cumulativeY = (int)getHeight() - cellPaddingVertical;
        for (int row = 0; row < rowCount; row++){
            cumulativeX = cellPaddingHorizontal;
            cumulativeY -= cellHeights[row];
            for (int column = 0; column < columnCount; column++) {
                Item cell = cells[column][row];
                if (cell != null){
                    cell.setPosition(cumulativeX, cumulativeY);
                }
                cumulativeX += cellWidths[column] + cellSpacing;
            }
            cumulativeY -= cellSpacing;
        }
    }

    public void clearCells()
    {
        for (int row = 0; row < rowCount; row++){
            for (int column = 0; column < columnCount; column++){
                Item cell = cells[column][row];
                if (cell != null){
                    removeItem(cell);
                    cells[column][row] = null;
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        drawBackground(batch, alpha);
    }

    private void drawBackground(Batch batch, float alpha)
    {
        if (background != null){
            background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }
}
