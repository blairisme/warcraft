package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.hud.control.Table;

import java.util.Collection;
import java.util.Objects;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPanel extends Item
{
    private Table container;
    private Provider<SelectionTile> tileProvider;

    public SelectionPanel(Provider<SelectionTile> tileProvider)
    {
        this.tileProvider = tileProvider;
        this.container = new Table(3, 3);
        this.container.setSize(176, 176); //TODO
        this.container.setCellPadding(3);
        this.container.setCellWidthMinimum(54);
        this.container.setCellHeightMinimum(53);
    }

    public void setBackground(Drawable background)
    {
        container.setBackground(background);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        container.draw(batch, alpha);
    }

    @Override
    public void update(float delta)
    {
        container.update(delta);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, new Identifier("Selection"))){
            Object currentSelection = getProperty(property);
            if (! Objects.equals(value, currentSelection)){
                setSelection((Collection<Item>) value);
            }
        }
        super.setProperty(property, value);
    }

    private void setSelection(Collection<Item> selection)
    {
        int x = 0;
        int y = 0;

        container.clear();
        for (Item selected: selection)
        {
            Drawable image = (Drawable)selected.getProperty(new Identifier("Icon"));
            if (image != null)
            {
                Item selectionTile = newSelectionTile(image);
                container.setCell(selectionTile, x, y);

                y = x != 2 ? y : y + 1;
                x = x == 2 ? 0 : x + 1;
            }
        }
    }

    //TODO: Change to set item. Have selection tile update health from item.
    private SelectionTile newSelectionTile(Drawable icon)
    {
        SelectionTile result = tileProvider.get();
        result.setImage(icon);
        result.setHealth(0.2f);
        result.setSize(54, 53);
        return result;
    }


    @Override //TODO: Investigate better implementation
    public void positionChanged()
    {
        container.setPosition(getX(), getY());
    }

    @Override //TODO: Investigate better implementation
    public void sizeChanged()
    {
        container.setSize(getWidth(), getHeight());
    }
}
