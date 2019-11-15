/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.flying;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.graphics.Renderable;
import com.evilbird.warcraft.object.unit.combatant.CombatantVessel;

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
    public void setStyle(String name) {
        super.setStyle(name);
        Skin skin = getSkin();
        FlyingUnitStyle style = skin.get(name, FlyingUnitStyle.class);
        this.shadow = style.shadow;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        super.draw(batch, alpha);
        shadow.draw(batch, alpha);
    }

    @Override
    public void update(float time) {
        super.update(time);
        shadow.update(time);
    }
}