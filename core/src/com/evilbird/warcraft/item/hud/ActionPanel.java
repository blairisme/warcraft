package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.action.Actions;
import com.evilbird.warcraft.item.hud.control.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPanel extends Item
{
    private Table table;
    private Provider<ActionTile> tileProvider;

    public ActionPanel(Provider<ActionTile> tileProvider)
    {
        this.tileProvider = tileProvider;
        this.table = new Table(3, 3);
        this.table.setBounds(0, 300, 176, 176); //TODO
        this.table.setCellPadding(3);
        this.table.setCellWidthMinimum(54);
        this.table.setCellHeightMinimum(46);
    }

    public void setBackground(TextureRegion texture)
    {
        Drawable drawable = new TextureRegionDrawable(texture);
        table.setBackground(drawable);
    }

    @Override
    public void draw(Batch batch, float alpha)
    {
        table.draw(batch, alpha);
    }

    @Override
    public void act(float delta)
    {
        table.act(delta);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        super.setProperty(property, value);

        if (Objects.equals(property, new Identifier("Selection"))){
            setSelection((Collection<Item>)value);
        }
    }

    private void setSelection(Collection<Item> selection)
    {
        Collection<Actions> actions = getActions(selection);
        Collection<Item> tiles = getTiles(actions);

        table.clear();
        table.setCells(tiles);
    }

    private Collection<Actions> getActions(Collection<Item> selection)
    {
        Collection<Actions> result = selection.isEmpty() ? EnumSet.noneOf(Actions.class) : EnumSet.allOf(Actions.class);
        for (Item item: selection){
            Collection<Actions> actions = getActions(item);
            result.retainAll(actions);
        }
        return result;
    }

    private Collection<Actions> getActions(Item item){
        Collection<Actions> actions = (Collection<Actions>)item.getProperty(new Identifier("Actions"));
        if (actions != null){
           return actions;
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<Actions> actions)
    {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (Actions action: actions){
            result.add(getTile(action));
        }
        return result;
    }

    private Item getTile(Actions action)
    {
        ActionTile result = tileProvider.get();
        result.setAction(action);
        result.setSize(54, 46);
        return result;
    }
}
