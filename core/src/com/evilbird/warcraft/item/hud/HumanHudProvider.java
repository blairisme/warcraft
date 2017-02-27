package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.AssetObjectProvider;

import javax.inject.Inject;

public class HumanHudProvider implements AssetObjectProvider<ItemGroup>
{
    private ActionPanelProvider actionPanelProvider;
    private ActionTileProvider actionTileProvider;
    private ResourcePanelProvider resourcePanelProvider;
    private SelectionPanelProvider selectionPanelProvider;
    private SelectionTileProvider selectionTileProvider;
    private HealthBarProvider healthBarProvider;

    @Inject
    public HumanHudProvider(
        ActionPanelProvider actionPanelProvider,
        ActionTileProvider actionTileProvider,
        ResourcePanelProvider resourceBarProvider,
        SelectionPanelProvider selectionPanelProvider,
        SelectionTileProvider selectionTileProvider,
        HealthBarProvider healthBarProvider)
    {
        this.actionPanelProvider = actionPanelProvider;
        this.actionTileProvider = actionTileProvider;
        this.resourcePanelProvider = resourceBarProvider;
        this.selectionPanelProvider = selectionPanelProvider;
        this.selectionTileProvider = selectionTileProvider;
        this.healthBarProvider = healthBarProvider;
    }

    @Override
    public void load()
    {
        actionPanelProvider.load();
        actionTileProvider.load();
        resourcePanelProvider.load();
        selectionPanelProvider.load();
        selectionTileProvider.load();
        healthBarProvider.load();
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
