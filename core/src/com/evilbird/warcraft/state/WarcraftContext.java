/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.game.GameContext;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftSeason;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Identifiers the context in which the game runs. E.G., a human campaign using
 * winter graphics.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WarcraftContextAdapter.class)
public class WarcraftContext implements GameContext
{
    private WarcraftFaction faction;
    private WarcraftSeason assets;

    public WarcraftContext(WarcraftFaction faction, WarcraftSeason assets) {
        this.faction = faction;
        this.assets = assets;
    }

    public WarcraftFaction getFaction() {
        return faction;
    }

    public WarcraftSeason getAssetSet() {
        return assets;
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
