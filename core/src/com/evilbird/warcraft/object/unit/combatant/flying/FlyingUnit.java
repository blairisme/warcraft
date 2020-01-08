/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.renderable.AlignedRenderable;
import com.evilbird.engine.common.graphics.renderable.Renderable;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.unit.combatant.CombatantVessel;

import static com.evilbird.engine.common.graphics.renderable.Alignment.BottomCenter;
import static com.evilbird.engine.common.graphics.renderable.EmptyRenderable.BlankRenderable;

/**
 * A {@link CombatantVessel} specialization representing a flying unit.
 *
 * @author Blair Butterworth
 */
public class FlyingUnit extends CombatantVessel
{
    private transient Renderable shadow;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link FlyingUnitStyle}.
     */
    public FlyingUnit(Skin skin) {
        super(skin);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Air;
    }

    @Override
    public void setHealth(float health) {
        super.setHealth(health);
        if (health == 0) {
            shadow = BlankRenderable;
        }
    }

    @Override
    public void setStyle(String name) {
        super.setStyle(name);
        Skin skin = getSkin();
        FlyingUnitStyle style = skin.get(name, FlyingUnitStyle.class);
        this.shadow = new AlignedRenderable(style.shadow, BottomCenter);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        shadow.draw(batch, position, size);
        super.draw(batch, alpha);
    }

    @Override
    public void update(float time) {
        super.update(time);
        shadow.update(time);
    }
}
