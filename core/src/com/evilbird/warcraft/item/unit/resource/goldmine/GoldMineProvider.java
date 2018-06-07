package com.evilbird.warcraft.item.unit.resource.goldmine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SilentSoundEffect;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollectionBuilder;
import com.evilbird.warcraft.item.common.texture.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.ResourceType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.resource.Resource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.gatherSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.idleSingualarSchema;

public class GoldMineProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public GoldMineProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/neutral/winter/gold_mine.png", Texture.class);
        assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
    }

    @Override
    public Item get() {
        Resource result = new Resource();
        result.setAvailableAnimations(getAnimations());
        result.setAvailableSounds(getSounds());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(2500.0f);
        result.setHealthMaximum(2500.0f);
        result.setIcon(getIcon());
        result.setName("Gold Mine");
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setType(UnitType.GoldMine);
        result.setResource(ResourceType.Gold, 2500f);
        result.setSize(96, 96);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get("data/textures/neutral/winter/gold_mine.png", Texture.class);
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, idleSingualarSchema(96, 96), general);
        builder.set(UnitAnimation.GatherGold, gatherSchema(96, 96), general);
        return builder.build();
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, "data/textures/neutral/perennial/icons.png", 184, 532, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<SoundIdentifier, SoundEffect>();
        sounds.put(UnitSound.GatherGold, new SilentSoundEffect());
        return sounds;
    }
}
