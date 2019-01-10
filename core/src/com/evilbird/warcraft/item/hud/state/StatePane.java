package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.site.BuildSite;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class StatePane extends ItemGroup
{
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
        setSize(176, 176); //TODO: scale flexibly
        setId(new NamedIdentifier("StatePane")); //TODO: use enum
        setType(new NamedIdentifier("StatePane"));
        setTouchable(Touchable.childrenOnly);
    }

    public void setSelection(Collection<Item> newSelection)
    {
        if (!Objects.equals(selection, newSelection))
        {
            selection = newSelection;
            clearItems();

            Collection<Item> items = removeHudElements(selection);
            if (items.size() == 1)
            {
                detailsPane.setItem(items.iterator().next());
                addItem(detailsPane);
            }
            else
            {
                selectionPane.setItems(items);
                addItem(selectionPane);
            }
        }
    }

    private Collection<Item> removeHudElements(Collection<Item> elements)
    {
        Collection<Item> result = new ArrayList<Item>(elements.size());
        for (Item element: elements){
            if (!(element instanceof BuildSite)){
                result.add(element);
            }
        }
        return result;
    }
}
