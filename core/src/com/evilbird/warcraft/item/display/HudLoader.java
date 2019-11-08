/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameService;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

public class HudLoader
{
    private DeviceDisplay display;
    private ItemFactory itemFactory;

    public HudLoader() {
        GameService service = GameService.getInstance();
        this.display = service.getDevice().getDeviceDisplay();
        this.itemFactory = service.getItemFactory();
    }

    @Inject
    public HudLoader(Device device, ItemFactory itemFactory) {
        this.display = device.getDeviceDisplay();
        this.itemFactory = itemFactory;
    }

    public ItemRoot get() {
        ScreenViewport viewport = new ScreenViewport();
        viewport.setUnitsPerPixel(display.getPixelUnits());

        ItemRoot result = new ItemRoot();
        result.setViewport(viewport);
        result.setIdentifier(HudType.Default);
        result.addItem(itemFactory.get(HudType.Default));
        return result;
    }
}
