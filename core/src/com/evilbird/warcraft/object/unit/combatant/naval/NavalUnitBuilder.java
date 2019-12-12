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
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.combatant.naval.animations.ShipAnimations;
import com.evilbird.warcraft.object.unit.combatant.naval.animations.SubmarineAnimations;

/**
 * Creates a new naval combatants whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class NavalUnitBuilder extends CombatantBuilder<RangedCombatant>
{
    private NavalUnitAssets assets;
    private UnitType type;

    public NavalUnitBuilder(NavalUnitAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
    }

    @Override
    protected RangedCombatant newCombatant(Skin skin) {
        if (type.isSubmarine()) {
            return new Submarine(skin);
        }
        if (type.isTransport()) {
            return new Transport(skin);
        }
        return new Ship(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return type.isSubmarine() ? new SubmarineAnimations(assets) : new ShipAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return new CombatantSounds(assets);
    }
}
