package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.hud.common.UnitPane;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionPane extends GridPane
{
    private Provider<UnitPane> tileProvider;

    public SelectionPane(Provider<UnitPane> tileProvider)
    {
        super(3, 3);
        this.tileProvider = tileProvider;
        setSize(176, 176); //TODO
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(53);
        setId(new NamedIdentifier("SelectionPane"));
        setType(new NamedIdentifier("SelectionPane"));
        setTouchable(Touchable.childrenOnly);
    }

    public void setItems(Collection<Item> selection)
    {
        clearCells();
        setCells(getUnitPanes(selection));
    }

    private Collection<Item> getUnitPanes(Collection<Item> items)
    {
        Collection<Item> result = new ArrayList<Item>(items.size());
        for (Item item: items){
            result.add(getUnitPane(item));
        }
        return result;
    }

    private Item getUnitPane(Item item)
    {
        UnitPane result = tileProvider.get();
        result.setItem(item);
        result.setSize(54, 53);
        return result;
    }
}
