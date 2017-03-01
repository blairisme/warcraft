package com.evilbird.engine.item.control;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import java.util.Collection;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class GridPanel extends ItemGroup
{
    private Drawable background;
    private Item[][] cells;
    private int cellPadding;
    private int cellSpacing;
    private int[] cellWidths;
    private int[] cellHeights;
    private int cellWidthMinimum;
    private int cellHeightMinimum;
    private int columnCount;
    private int rowCount;

    public GridPanel(int columnCount, int rowCount)
    {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.cells = new Item[columnCount][rowCount];
        this.cellWidths = new int[columnCount];
        this.cellHeights = new int[rowCount];
        this.cellPadding = 0;
        this.cellSpacing = 0;
        this.cellWidthMinimum = 0;
        this.cellHeightMinimum = 0;
        this.background = null;
    }

    public void setBackground(Drawable background)
    {
        this.background = background;
    }

    public void setCellPadding(int cellPadding)
    {
        this.cellPadding = cellPadding;
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
        int cumulativeX = cellPadding;
        int cumulativeY = cellPadding;
        for (int row = rowCount - 1; row >= 0; row--){
            for (int column = 0; column < columnCount; column++) {
                Item cell = cells[column][row];
                if (cell != null){
                    cell.setPosition(cumulativeX, cumulativeY);
                }
                cumulativeX += cellWidths[column] + cellSpacing;
            }
            cumulativeX = cellPadding;
            cumulativeY += cellHeights[row] + cellSpacing;
        }
    }

    public void clear()
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
