package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO:
public class HumanHudProvider implements AssetObjectProvider<ItemRoot>
{
    private ActionPanelProvider actionPanelProvider;
    private ActionButtonProvider actionButtonProvider;
    private ResourcePanelProvider resourcePanelProvider;
    private SelectionPanelProvider selectionPanelProvider;
    private SelectionTileProvider selectionTileProvider;
    private HealthBarProvider healthBarProvider;
    private MinimapPanelProvider minimapPanelProvider;
    private Provider<ControlPanel> controlPanelProvider;

    @Inject
    public HumanHudProvider(
        ActionPanelProvider actionPanelProvider,
        ActionButtonProvider actionButtonProvider,
        ResourcePanelProvider resourceBarProvider,
        SelectionPanelProvider selectionPanelProvider,
        SelectionTileProvider selectionTileProvider,
        HealthBarProvider healthBarProvider,
        MinimapPanelProvider minimapPanelProvider,
        Provider<ControlPanel> controlPanelProvider)
    {
        this.actionPanelProvider = actionPanelProvider;
        this.actionButtonProvider = actionButtonProvider;
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
        actionButtonProvider.load();
        resourcePanelProvider.load();
        selectionPanelProvider.load();
        selectionTileProvider.load();
        healthBarProvider.load();
        minimapPanelProvider.load();
    }

    @Override
    public ItemRoot get()
    {
        ItemRoot hud = new ItemRoot();
        hud.addItem(resourcePanelProvider.get());
        hud.addItem(controlPanelProvider.get());
        return hud;
    }
}
