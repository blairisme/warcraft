package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.action.ActionButtonProvider;
import com.evilbird.warcraft.item.hud.action.ActionPaneProvider;
import com.evilbird.warcraft.item.hud.building.BuildingSiteProvider;
import com.evilbird.warcraft.item.hud.common.HealthBarProvider;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;
import com.evilbird.warcraft.item.hud.control.ControlPane;
import com.evilbird.warcraft.item.hud.minimap.MinimapPaneProvider;
import com.evilbird.warcraft.item.hud.resource.ResourcePanelProvider;
import com.evilbird.warcraft.item.hud.state.DetailsPaneProvider;
import com.evilbird.warcraft.item.hud.state.SelectionPaneProvider;
import com.evilbird.warcraft.item.hud.state.StatePane;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.item.hud.HudControls.ActionButton;
import static com.evilbird.warcraft.item.hud.HudControls.ActionPane;
import static com.evilbird.warcraft.item.hud.HudControls.ControlPane;
import static com.evilbird.warcraft.item.hud.HudControls.DetailsPane;
import static com.evilbird.warcraft.item.hud.HudControls.HealthBar;
import static com.evilbird.warcraft.item.hud.HudControls.MinimapPane;
import static com.evilbird.warcraft.item.hud.HudControls.ResourcePane;
import static com.evilbird.warcraft.item.hud.HudControls.SelectionPane;
import static com.evilbird.warcraft.item.hud.HudControls.StatePane;
import static com.evilbird.warcraft.item.hud.HudControls.UnitPane;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class HudControlProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public HudControlProvider(
        ActionButtonProvider actionButtonProvider,
        ActionPaneProvider actionPaneProvider,
        Provider<ControlPane> controlPanelProvider,
        DetailsPaneProvider detailsPaneProvider,
        HealthBarProvider healthBarProvider,
        MinimapPaneProvider minimapPaneProvider,
        ResourcePanelProvider resourcePaneProvider,
        SelectionPaneProvider selectionPaneProvider,
        Provider<StatePane> statePaneProvider,
        UnitPaneProvider unitPaneProvider,
        BuildingSiteProvider buildingSiteProvider)
    {
        addProvider(ActionButton, actionButtonProvider);
        addProvider(ActionPane, actionPaneProvider);
        addProvider(ControlPane, controlPanelProvider);
        addProvider(DetailsPane, detailsPaneProvider);
        addProvider(HealthBar, healthBarProvider);
        addProvider(MinimapPane, minimapPaneProvider);
        addProvider(ResourcePane, resourcePaneProvider);
        addProvider(SelectionPane, selectionPaneProvider);
        addProvider(StatePane, statePaneProvider);
        addProvider(UnitPane, unitPaneProvider);
        addProvider(buildingSiteProvider);
    }
}
