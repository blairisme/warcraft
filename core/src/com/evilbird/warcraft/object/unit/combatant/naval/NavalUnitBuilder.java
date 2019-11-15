/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.naval;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.ranged.RangedUnitAssets;
import com.evilbird.warcraft.object.unit.combatant.ranged.RangedUnitSounds;

/**
 * Creates a new naval combatants whose visual and audible presentation is
 * defined by the given {@link RangedUnitAssets}.
 *
 * @author Blair Butterworth
 */
public class NavalUnitBuilder extends CombatantBuilder<RangedCombatant>
{
    private RangedUnitAssets assets;
    private UnitType type;

    public NavalUnitBuilder(RangedUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
    }

    @Override
    protected RangedCombatant newCombatant(Skin skin) {
        return type.isSubmarine() ? new Submarine(skin) : new Ship(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return type.isSubmarine() ? new SubmarineAnimations(assets) : new ShipAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return new RangedUnitSounds(assets);
    }
}
