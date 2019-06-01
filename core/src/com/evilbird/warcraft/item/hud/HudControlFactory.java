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
import com.evilbird.warcraft.item.hud.control.ControlPaneFactory;
import com.evilbird.warcraft.item.hud.resource.ResourcePaneFactory;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.hud.HudControl.ControlPane;
import static com.evilbird.warcraft.item.hud.HudControl.ResourcePane;

/**
 * Instances of this factory create user interface controls.
 *
 * @author Blair Butterworth
 */
public class HudControlFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public HudControlFactory(ControlPaneFactory controlPaneFactory,ResourcePaneFactory resourcePaneProvider) {
        addProvider(ControlPane, controlPaneFactory);
        addProvider(ResourcePane, resourcePaneProvider);
    }
}
