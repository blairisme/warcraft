package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.hud.HudControls;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HumanHudProvider implements AssetProvider<ItemRoot>
{
    private ItemFactory itemFactory;

    @Inject
    public HumanHudProvider(ItemFactory itemFactory)
    {
        this.itemFactory = itemFactory;
    }

    @Override
    public void load()
    {
    }

    @Override
    public ItemRoot get()
    {
        ItemRoot hud = new ItemRoot();
        hud.addItem(itemFactory.newItem(HudControls.ResourcePane));
        hud.addItem(itemFactory.newItem(HudControls.ControlPane));
        return hud;
    }
}