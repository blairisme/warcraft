/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
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
