package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemGroup;

import java.util.Collection;
import java.util.Objects;

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
        clearItems();
        if (selection.size() == 1){
            detailsPane.setItem(selection.iterator().next());
            addItem(detailsPane);
        }else {
            selectionPane.setItems(selection);
            addItem(selectionPane);
        }
    }
}
