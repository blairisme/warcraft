package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.utility.AssetObjectProvider;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO:
public class HumanHudProvider implements AssetObjectProvider<ItemGroup>
{
    private ActionPanelProvider actionPanelProvider;
    private ActionTileProvider actionTileProvider;
    private ResourcePanelProvider resourcePanelProvider;
    private SelectionPanelProvider selectionPanelProvider;
    private SelectionTileProvider selectionTileProvider;
    private HealthBarProvider healthBarProvider;
    private MinimapPanelProvider minimapPanelProvider;
    private Provider<ControlPanel> controlPanelProvider;

    @Inject
    public HumanHudProvider(
        ActionPanelProvider actionPanelProvider,
        ActionTileProvider actionTileProvider,
        ResourcePanelProvider resourceBarProvider,
        SelectionPanelProvider selectionPanelProvider,
        SelectionTileProvider selectionTileProvider,
        HealthBarProvider healthBarProvider,
        MinimapPanelProvider minimapPanelProvider,
        Provider<ControlPanel> controlPanelProvider)
    {
        this.actionPanelProvider = actionPanelProvider;
        this.actionTileProvider = actionTileProvider;
        this.resourcePanelProvider = resourceBarProvider;
        this.selectionPanelProvider = selectionPanelProvider;
        this.selectionTileProvider = selectionTileProvider;
        this.healthBarProvider = healthBarProvider;
        this.minimapPanelProvider = minimapPanelProvider;
        this.controlPanelProvider = controlPanelProvider;
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
        minimapPanelProvider.load();
    }

    @Override
    public ItemGroup get()
    {
        ItemGroup hud = new ItemGroup();
        hud.addActor(resourcePanelProvider.get());
        hud.addActor(controlPanelProvider.get());
        return hud;
    }
}
