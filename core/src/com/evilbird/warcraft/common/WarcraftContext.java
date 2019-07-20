/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.common;

import com.evilbird.engine.common.function.ParameterizedSupplier;
import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.engine.game.GameContext;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Identifiers the context in which the game runs. E.G., a human campaign using
 * winter graphics.
 *
 * @author Blair Butterworth
 */
public class WarcraftContext implements GameContext, ParameterizedSupplier<String, String>
{
    private WarcraftFaction faction;
    private WarcraftAssetSet assets;

    public WarcraftContext(WarcraftFaction faction, WarcraftAssetSet assets) {
        this.faction = faction;
        this.assets = assets;
    }

    public WarcraftFaction getFaction() {
        return faction;
    }

    public WarcraftAssetSet getAssetSet() {
        return assets;
    }

    @Override
    public String get(String key) {
        if (key.equals("faction")) {
            return CaseUtils.toSnakeCase(faction.name());
        }
        if (key.equals("assets")) {
            return CaseUtils.toSnakeCase(assets.name());
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        WarcraftContext that = (WarcraftContext)obj;
        return new EqualsBuilder()
            .append(faction, that.faction)
            .append(assets, that.assets)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(faction)
            .append(assets)
            .toHashCode();
    }
}
