package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;
import javax.inject.Provider;

//TODO:
public class HumanHudProvider implements AssetObjectProvider<ItemRoot>
{
    private ActionPaneProvider actionPaneProvider;
    private ActionButtonProvider actionButtonProvider;
    private ResourcePanelProvider resourcePanelProvider;
    private SelectionPaneProvider selectionPaneProvider;
    private UnitPaneProvider unitPaneProvider;
    private HealthBarProvider healthBarProvider;
    private MinimapPaneProvider minimapPaneProvider;
    private DetailsPaneProvider detailsPaneProvider;
    private Provider<ControlPane> controlPanelProvider;
    private Provider<StatePane> statePanelProvider;

    @Inject
    public HumanHudProvider(
        ActionPaneProvider actionPaneProvider,
        ActionButtonProvider actionButtonProvider,
        ResourcePanelProvider resourceBarProvider,
        SelectionPaneProvider selectionPaneProvider,
        UnitPaneProvider unitPaneProvider,
        HealthBarProvider healthBarProvider,
        MinimapPaneProvider minimapPaneProvider,
        DetailsPaneProvider detailsPaneProvider,
        Provider<ControlPane> controlPanelProvider)
    {
        this.actionPaneProvider = actionPaneProvider;
        this.actionButtonProvider = actionButtonProvider;
        this.resourcePanelProvider = resourceBarProvider;
        this.selectionPaneProvider = selectionPaneProvider;
        this.unitPaneProvider = unitPaneProvider;
        this.healthBarProvider = healthBarProvider;
        this.minimapPaneProvider = minimapPaneProvider;
        this.detailsPaneProvider = detailsPaneProvider;
        this.controlPanelProvider = controlPanelProvider;
    }

    @Override
    public void load()
    {
        actionPaneProvider.load();
        actionButtonProvider.load();
        resourcePanelProvider.load();
        selectionPaneProvider.load();
        unitPaneProvider.load();
        healthBarProvider.load();
        minimapPaneProvider.load();
        detailsPaneProvider.load();
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
