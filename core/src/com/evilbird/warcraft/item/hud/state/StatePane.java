package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class StatePane extends ItemGroup
{
    private static final Identifier SELECTION_PROPERTY = new Identifier("Selection");

    private DetailsPane detailsPane;
    private SelectionPane selectionPane;
    private Collection<Item> selection;

    @Inject
    public StatePane(
        DetailsPaneProvider detailsPaneProvider,
        SelectionPaneProvider selectionPaneProvider)
    {
        this.detailsPane = detailsPaneProvider.get();
        this.selectionPane = selectionPaneProvider.get();
        addItem(selectionPane);
        setSize(176, 176); //TODO
        setId(new Identifier("StatePane"));
        setType(new Identifier("StatePane"));
        setTouchable(Touchable.childrenOnly);
    }

    public Collection<Item> getSelection()
    {
        return selection;
    }

    public void setSelection(Collection<Item> newSelection)
    {
        if (! Objects.equals(selection, newSelection))
        {
            selection = newSelection;
            clearItems();

            if (selection.size() == 1)
            {
                detailsPane.setItem(selection.iterator().next());
                addItem(detailsPane);
            }
            else
            {
                selectionPane.setItems(selection);
                addItem(selectionPane);
            }
        }
    }

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
}
