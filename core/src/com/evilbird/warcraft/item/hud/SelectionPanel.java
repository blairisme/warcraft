package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPanel;

import java.util.Collection;
import java.util.Objects;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPanel extends GridPanel
{
    private Provider<SelectionTile> tileProvider;

    public SelectionPanel(Provider<SelectionTile> tileProvider)
    {
        super(3, 3);
        this.tileProvider = tileProvider;
        setSize(176, 176); //TODO
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(53);
        setId(new Identifier("SelectionPanel"));
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

        clear();
        for (Item selected: selection)
        {
            Drawable image = (Drawable)selected.getProperty(new Identifier("Icon"));
            if (image != null)
            {
                Item selectionTile = newSelectionTile(image);
                setCell(selectionTile, x, y);

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
}
