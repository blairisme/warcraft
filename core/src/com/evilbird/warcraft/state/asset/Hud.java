/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.asset;

import com.evilbird.warcraft.item.hud.HudControl;

import java.util.Arrays;
import java.util.Collection;

public enum Hud implements HudDefinition
{
    Human (HudControl.ResourcePane, HudControl.ControlPane),
    Orc   ();

    private Collection<HudControl> controls;

    Hud(HudControl ... controls) {
        this(Arrays.asList(controls));
    }

    Hud(Collection<HudControl> controls) {
        this.controls = controls;
    }

    @Override
    public Collection<HudControl> getControls() {
        return controls;
    }
}