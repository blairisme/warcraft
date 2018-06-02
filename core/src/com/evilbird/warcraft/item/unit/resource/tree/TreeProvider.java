package com.evilbird.warcraft.item.unit.resource.tree;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationUtils;
import com.evilbird.warcraft.item.common.texture.TextureUtils;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class TreeProvider implements AssetProvider<Tree>
{
    private AssetManager assets;

    @Inject
    public TreeProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/winter/terrain.png", Texture.class);
    }

    @Override
    public Tree get()
    {
        Tree result = new Tree();
        result.setAvailableAnimations(getAnimations());
        result.setAvailableSounds(getSounds());
        result.setAnimation(UnitAnimation.Idle);
        result.setIcon(getIcon());
        result.setName("Tree");
        result.setSelected(false);
        result.setSelectable(false);
        result.setTouchable(Touchable.enabled);
        result.setType(new NamedIdentifier("Wood"));
        result.setResource(ResourceType.Wood, 100f);
        result.setSize(32, 32);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations()
    {
        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);
        TextureRegion idleTexture =  new TextureRegion(texture, 7 * 32, 0, 32, 32);
        TextureRegion deathTexture =  new TextureRegion(texture, 448, 224, 32, 32);

        DirectionalAnimation idleAnimation = AnimationUtils.getAnimation(idleTexture);
        DirectionalAnimation deathAnimation = AnimationUtils.getAnimation(deathTexture);

        Map<AnimationIdentifier, DirectionalAnimation> animations = new HashMap<AnimationIdentifier, DirectionalAnimation>();
        animations.put(UnitAnimation.Idle, idleAnimation);
        animations.put(UnitAnimation.Dead, deathAnimation);
        animations.put(UnitAnimation.GatherWood, idleAnimation);

        return animations;
    }

    private Drawable getIcon()
    {
        return TextureUtils.getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 684, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds()
    {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<SoundIdentifier, SoundEffect>();
        sounds.put(UnitSound.GatherWood, new SilentSoundEffect());
        return sounds;
    }
}
