/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.siege;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

/**
 * Creates a new siege combatant whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class SiegeUnitBuilder extends CombatantBuilder<RangedCombatant>
{
    private SiegeUnitAssets assets;

    public SiegeUnitBuilder(SiegeUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
    }

    @Override
    protected RangedCombatant newCombatant(Skin skin) {
        return new RangedCombatant(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return new SiegeUnitAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return new CombatantSounds(assets);
    }
}
