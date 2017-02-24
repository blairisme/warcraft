package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.AssetObjectProvider;

import javax.inject.Inject;

public class HumanHud implements AssetObjectProvider<ItemGroup>
{
    private ActionPanelProvider actionPanelProvider;
    private ResourcePanelProvider resourcePanelProvider;
    private SelectionPanelProvider selectionPanelProvider;

    @Inject
    public HumanHud(
        ActionPanelProvider actionPanelProvider,
        ResourcePanelProvider resourceBarProvider,
        SelectionPanelProvider selectionPanelProvider)
    {
        this.actionPanelProvider = actionPanelProvider;
        this.resourcePanelProvider = resourceBarProvider;
        this.selectionPanelProvider = selectionPanelProvider;
    }

    @Override
    public void load()
    {
        actionPanelProvider.load();
        resourcePanelProvider.load();
        selectionPanelProvider.load();
    }

    @Override
    public ItemGroup get()
    {
        ItemGroup hud = new ItemGroup();
        hud.addActor(actionPanelProvider.get());
        hud.addActor(resourcePanelProvider.get());
        hud.addActor(selectionPanelProvider.get());
        return hud;
    }
}
