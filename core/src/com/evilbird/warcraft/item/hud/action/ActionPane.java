package com.evilbird.warcraft.item.hud.action;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.world.unit.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridPane
{
    private ActionButtonProvider buttonProvider;
    private Collection<Item> selection;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider)
    {
        super(3, 3);
        this.buttonProvider = buttonProvider;
        this.selection = Collections.emptyList();

        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setId(new Identifier("ActionPane"));
        setType(new Identifier("ActionPane"));
        setTouchable(Touchable.childrenOnly);
    }

    public Collection<Item> getSelection()
    {
        return selection;
    }

    public void setSelection(Collection<Item> newSelection)
    {
        if (! Objects.equals(selection, newSelection)){
            selection = newSelection;

            Collection<ActionType> actions = getActions(selection);
            Collection<Item> tiles = getTiles(actions);

            clearCells();
            setCells(tiles);
        }
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
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getActions();
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<ActionType> actions)
    {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (ActionType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionType action)
    {
        ActionButton result = buttonProvider.get();
        result.setAction(action);
        result.setSize(54, 46);
        return result;
    }

    /*

    private static final Identifier SELECTION_PROPERTY = new Identifier("Selection");

    @Override
    public Object getProperty(Identifier property)
    {
        if (Objects.equals(property, SELECTION_PROPERTY)){
            return getSelection();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(Identifier property, Object value)
    {
        if (Objects.equals(property, SELECTION_PROPERTY)){
            setSelection((Collection<Item>)value);
        }
        else{
            super.setProperty(property, value);
        }
    }
    */
}
