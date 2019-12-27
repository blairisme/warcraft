/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.audio.sound.Sound;

import static com.evilbird.engine.audio.sound.SilentSound.SilentSoundEffect;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Arrow;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Axe;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Bolt;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Cannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.DaemonFire;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Fireball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingCannonball;
import static com.evilbird.warcraft.object.projectile.ProjectileType.FlamingRock;
import static com.evilbird.warcraft.object.projectile.ProjectileType.GryphonHammer;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Lightning;
import static com.evilbird.warcraft.object.projectile.ProjectileType.Torpedo;
import static com.evilbird.warcraft.object.projectile.ProjectileType.TouchOfDeath;

/**
 * Provides access to the assets that are required to display a
 * {@link Projectile}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class ProjectileAssets extends AssetBundle
{
    private static final String TEXTURE_ID = "Texture";
    private static final String SOUND_ID = "Sound";

    public ProjectileAssets(AssetManager assetManager) {
        super(assetManager);
        registerTextures();
        registerSounds();
    }

    private void registerTextures() {
        register(Arrow.name() + TEXTURE_ID, "data/textures/common/projectile/arrow.png");
        register(Axe.name() + TEXTURE_ID, "data/textures/common/projectile/axe.png");
        register(Bolt.name() + TEXTURE_ID, "data/textures/common/projectile/bolt.png");
        register(Cannonball.name() + TEXTURE_ID, "data/textures/common/projectile/cannonball.png");
        register(DaemonFire.name() + TEXTURE_ID, "data/textures/common/projectile/daemon_fire.png");
        register(Fireball.name() + TEXTURE_ID, "data/textures/common/projectile/fireball.png");
        register(FlamingCannonball.name() + TEXTURE_ID, "data/textures/common/projectile/flaming_cannonball.png");
        register(FlamingRock.name() + TEXTURE_ID, "data/textures/common/projectile/flaming_rock.png");
        register(GryphonHammer.name() + TEXTURE_ID, "data/textures/common/projectile/gryphon_hammer.png");
        register(Lightning.name() + TEXTURE_ID, "data/textures/common/projectile/lightning.png");
        register(Torpedo.name() + TEXTURE_ID, "data/textures/common/projectile/torpedo.png");
        register(TouchOfDeath.name() + TEXTURE_ID, "data/textures/common/projectile/touch_of_death.png");
    }

    private void registerSounds() {
        register(Arrow.name() + SOUND_ID, "data/sounds/common/projectile/arrow.mp3");
        register(Bolt.name() + SOUND_ID, "data/sounds/common/projectile/siege.mp3");
        register(Cannonball.name() + SOUND_ID, "data/sounds/common/projectile/fireball.mp3");
        register(DaemonFire.name() + SOUND_ID, "data/sounds/common/projectile/fireball.mp3");
        register(Fireball.name() + SOUND_ID, "data/sounds/common/projectile/fireball.mp3");
        register(FlamingCannonball.name() + SOUND_ID, "data/sounds/common/projectile/siege.mp3");
        register(FlamingRock.name() + SOUND_ID, "data/sounds/common/projectile/siege.mp3");
        register(GryphonHammer.name() + SOUND_ID, "data/sounds/common/projectile/fireball.mp3");
        register(Lightning.name() + SOUND_ID, "data/sounds/common/projectile/fireball.mp3");
        register(Torpedo.name() + SOUND_ID, "data/sounds/common/projectile/siege.mp3");
    }
    
    public Texture getBaseTexture(ProjectileType type) {
        String id = type.name() + TEXTURE_ID;
        return getTexture(id);
    }

    public Sound getHitSound(ProjectileType type) {
        String id = type.name() + SOUND_ID;
        return isRegistered(id) ? getSoundEffect(id) : SilentSoundEffect;
    }
}
