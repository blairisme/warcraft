package com.evilbird.warcraft.item.unit.resource.tree;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.animated.AnimationIdentifier;
import com.evilbird.engine.item.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollectionBuilder;
import com.evilbird.warcraft.item.common.animation.AnimationSchemas;
import com.evilbird.warcraft.item.common.texture.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class TreeProvider implements AssetProvider<Tree>
{
    private AssetManager assets;

    @Inject
    public TreeProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/neutral/winter/terrain.png", Texture.class);
    }

    @Override
    public Tree get() {
        Tree result = new Tree();
        result.setAvailableAnimations(getAnimations());
        result.setAvailableSounds(getSounds());
        result.setAnimation(UnitAnimation.Dead);
        result.setIcon(getIcon());
        result.setName("Tree");
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setType(UnitType.Tree);
        result.setResource(ResourceType.Wood, 100f);
        result.setHealth(100.0f);
        result.setHealthMaximum(100.0f);
        result.setSize(32, 32);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Dead, AnimationSchemas.treeStumpSchema(), texture);
        return builder.build();
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, "data/textures/neutral/perennial/icons.png", 46, 684, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<SoundIdentifier, SoundEffect>();
        sounds.put(UnitSound.GatherWood, new SilentSoundEffect());
        return sounds;
    }
}
