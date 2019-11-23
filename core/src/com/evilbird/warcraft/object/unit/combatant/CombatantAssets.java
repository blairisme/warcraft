/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.assets.AssetBundle;
import com.evilbird.engine.common.assets.SyntheticTexture;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Map;

import static com.evilbird.engine.common.assets.SyntheticTextureParameters.withColour;
import static com.evilbird.engine.common.audio.sound.SilentSound.SilentSoundEffect;
import static com.evilbird.engine.common.collection.Maps.of;
import static com.evilbird.engine.common.graphics.Colours.FOREST_GREEN;
import static com.evilbird.engine.common.graphics.Colours.LIGHT_BLUE;
import static com.evilbird.engine.common.text.CaseUtils.toSnakeCase;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensionName;
import static com.evilbird.warcraft.object.unit.UnitDimensions.getDimensions;
import static com.evilbird.warcraft.object.unit.UnitType.Daemon;
import static com.evilbird.warcraft.object.unit.UnitType.Dragon;
import static com.evilbird.warcraft.object.unit.UnitType.Mage;

/**
 * Defines the assets that are required to display a {@link Combatant}, as well
 * as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class CombatantAssets extends AssetBundle
{
    private GridPoint2 dimensions;

    /**
     * Constructs a new instance of this class given an {@link AssetManager}
     * from which assets will be obtained and the {@link UnitType} whose assets
     * will be obtained.
     *
     * @param manager   an asset manager used to load and unload assets.
     * @param type      the type of unit whose assets will be loaded and
     *                  unloaded.
     *
     * @throws IllegalArgumentException if either the given asset manager or
     *                                  unit type is {@code null}.
     */
    public CombatantAssets(AssetManager manager, UnitType type) {
        super(manager, assetPathVariables(type));
        dimensions = getDimensions(type);
        registerTextures();
        registerGeneralSounds();
        registerAttackSounds(type);
    }

    private static Map<String, String> assetPathVariables(UnitType type) {
        return of("name", toSnakeCase(type.name()),
                "faction", toSnakeCase(type.getFaction().name()),
                "size", getDimensionName(type));
    }

    private void registerTextures() {
        register("base", "data/textures/${faction}/unit/${name}.png");
        register("decompose", "data/textures/common/unit/decompose.png");
        registerOptional("mask", "data/textures/${faction}/unit/${name}_mask.png");
        register("selection", "selection_${size}", SyntheticTexture.class, withColour(FOREST_GREEN, dimensions));
        register("highlight", "highlight_${size}", SyntheticTexture.class, withColour(LIGHT_BLUE, dimensions));
    }

    private void registerGeneralSounds() {
        registerOptional("dead", "data/sounds/${faction}/unit/common/dead/1.mp3");
        registerOptional("capture", "data/sounds/${faction}/unit/common/capture/1.mp3");
        registerOptional("rescue", "data/sounds/${faction}/unit/common/rescue/1.mp3");

        registerOptional("ready", "data/sounds/${faction}/unit/${name}/ready/1.mp3");
        registerOptionalSequence("acknowledge", "data/sounds/${faction}/unit/${name}/acknowledge/", ".mp3", 5);
        registerOptionalSequence("selected", "data/sounds/${faction}/unit/${name}/selected/", ".mp3", 6);
    }

    private void registerAttackSounds(UnitType type) {
        if (type == Dragon || type == Daemon || type == Mage) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/fireball/", ".mp3", 1);
        }if (type.isSiege() || type.isWarship()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/siege/", ".mp3", 1);
        } else if (type.isLandGatherer()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/fist/", ".mp3", 1);
        } else if (type.isOgre()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/punch/", ".mp3", 1);
        } else if (type.isMelee()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/sword/", ".mp3", 3);
        } else if (type.isRanged() && type.isHuman()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/bow/", ".mp3", 1);
        } else if (type.isRanged() && type.isOrc()) {
            registerOptionalSequence("attack", "data/sounds/common/unit/attack/axe/", ".mp3", 1);
        }
    }

    public Texture getBaseTexture() {
        return getTexture("base");
    }

    public Texture getMaskTexture() {
        return getOptionalTexture("mask");
    }

    public Texture getDecomposeTexture() {
        return getTexture("decompose");
    }

    public Texture getSelectionTexture() {
        return getSyntheticTexture("selection");
    }

    public Texture getHighlightTexture() {
        return getSyntheticTexture("highlight");
    }

    public Sound getAcknowledgeSound() {
        return getSoundEffectSet("acknowledge", 5);
    }

    public Sound getAttackSound() {
        return isRegistered("attack-1") ? getSoundEffectSet("attack", 3) : SilentSoundEffect; //TODO
    }

    public Sound getDieSound() {
        return isRegistered("dead") ? getSoundEffect("dead") : SilentSoundEffect;
    }

    public Sound getReadySound() {
        return getSoundEffect("ready");
    }

    public Sound getSelectedSound() {
        return getSoundEffectSet("selected", 3);
    }

    public Sound getCaptureSound() {
        return getSoundEffect("capture");
    }

    public Sound getRescueSound() {
        return getSoundEffect("rescue");
    }

    public GridPoint2 getSize() {
        return dimensions;
    }
}
