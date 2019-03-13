/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.hud;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.hud.HudControl;

import java.util.Arrays;
import java.util.Collection;

public enum HudStateIdentifier implements Identifier
{
    Human (HudControl.ResourcePane, HudControl.ControlPane),
    Orc   ();

    private Collection<HudControl> controls;

    HudStateIdentifier(HudControl ... controls) {
        this(Arrays.asList(controls));
    }

    HudStateIdentifier(Collection<HudControl> controls) {
        this.controls = controls;
    }

    public Collection<HudControl> getControls() {
        return controls;
    }
}