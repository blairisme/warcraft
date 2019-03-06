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

import java.util.Collection;

public interface HudDefinition
{
    Collection<HudControl> getControls();
}
