package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.action.ActionType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridPane
{
    private Provider<ActionButton> buttonProvider;

    @Inject
    public ActionPane(Provider<ActionButton> buttonProvider)
    {
        super(3, 3);
        this.buttonProvider = buttonProvider;
        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setId(new Identifier("ActionPane"));
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
        Collection<ActionType> actions = getActions(selection);
        Collection<Item> tiles = getTiles(actions, selection);

        clearCells();
        setCells(tiles);
    }

    private Collection<ActionType> getActions(Collection<Item> selection)
    {
        Collection<ActionType> result = selection.isEmpty() ? EnumSet.noneOf(ActionType.class) : EnumSet.allOf(ActionType.class);
        for (Item item: selection){
            Collection<ActionType> actions = getActions(item);
            result.retainAll(actions);
        }
        return result;
    }

    private Collection<ActionType> getActions(Item item){
        Collection<ActionType> actions = (Collection<ActionType>)item.getProperty(new Identifier("Actions"));
        if (actions != null){
           return actions;
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<ActionType> actions, Collection<Item> items)
    {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (ActionType action: actions){
            result.add(getButton(action, items));
        }
        return result;
    }

    private ActionButton getButton(ActionType action, Collection<Item> items)
    {
        ActionButton result = buttonProvider.get();
        result.setAction(action);
        result.setItems(items);
        result.setSize(54, 46);
        return result;
    }
}
