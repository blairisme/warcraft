/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.CombatantSounds;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.animations.AssaultCasterAnimations;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.animations.EtherealCasterAnimations;

import static com.evilbird.warcraft.object.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;

/**
 * Creates a new spell caster whose visual and audible presentation is
 * defined by the given {@link CombatantAssets}.
 *
 * @author Blair Butterworth
 */
public class SpellCasterBuilder extends CombatantBuilder<SpellCaster>
{
    private UnitType type;
    private SpellCasterAssets assets;

    public SpellCasterBuilder(SpellCasterAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
    }

    @Override
    protected SpellCaster newCombatant(Skin skin) {
        return new SpellCaster(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return isEtherial() ? new EtherealCasterAnimations(assets) : new AssaultCasterAnimations(assets);
    }

    private boolean isEtherial() {
        return type == Mage || type == DeathKnight;
    }

    @Override
    protected SoundCatalog newSounds() {
        return new CombatantSounds(assets);
    }
}
