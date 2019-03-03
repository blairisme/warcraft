/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.common.HealthBarProvider;
import com.evilbird.warcraft.item.hud.common.UnitPaneProvider;
import com.evilbird.warcraft.item.hud.control.ControlPane;
import com.evilbird.warcraft.item.hud.control.actions.ActionButtonProvider;
import com.evilbird.warcraft.item.hud.control.actions.ActionPaneProvider;
import com.evilbird.warcraft.item.hud.control.menu.MenuPane;
import com.evilbird.warcraft.item.hud.control.menu.MenuPaneFactory;
import com.evilbird.warcraft.item.hud.control.minimap.MinimapPaneProvider;
import com.evilbird.warcraft.item.hud.control.status.DetailsPaneProvider;
import com.evilbird.warcraft.item.hud.control.status.SelectionPaneProvider;
import com.evilbird.warcraft.item.hud.control.status.StatePane;
import com.evilbird.warcraft.item.hud.control.status.building.BuildingProgressProvider;
import com.evilbird.warcraft.item.hud.resource.ResourcePanelProvider;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.warcraft.item.hud.HudControl.*;

/**
 * Instances of this factory create user interface controls.
 *
 * @author Blair Butterworth
 */
public class HudControlProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public HudControlProvider(
        ActionButtonProvider actionButtonProvider,
        ActionPaneProvider actionPaneProvider,
        BuildingProgressProvider buildingProgressProvider,
        Provider<ControlPane> controlPanelProvider,
        DetailsPaneProvider detailsPaneProvider,
        HealthBarProvider healthBarProvider,
        MinimapPaneProvider minimapPaneProvider,
        ResourcePanelProvider resourcePaneProvider,
        SelectionPaneProvider selectionPaneProvider,
        Provider<StatePane> statePaneProvider,
        UnitPaneProvider unitPaneProvider,
        MenuPaneFactory menuPaneFactory)
    {
        addProvider(ActionButton, actionButtonProvider);
        addProvider(ActionPane, actionPaneProvider);
        addProvider(ControlPane, controlPanelProvider);
        addProvider(DetailsPane, detailsPaneProvider);
        addProvider(HealthBar, healthBarProvider);
        addProvider(BuildingBar, buildingProgressProvider);
        addProvider(MenuPane, menuPaneFactory);
        addProvider(MinimapPane, minimapPaneProvider);
        addProvider(ResourcePane, resourcePaneProvider);
        addProvider(SelectionPane, selectionPaneProvider);
        addProvider(StatePane, statePaneProvider);
        addProvider(UnitPane, unitPaneProvider);
    }
}
