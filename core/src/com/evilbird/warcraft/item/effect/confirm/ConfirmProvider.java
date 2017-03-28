package com.evilbird.warcraft.item.effect.confirm;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Animation;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.warcraft.common.AnimationBuilder;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.AnimationLayouts.effectLayout;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ConfirmProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public ConfirmProvider(Device device)
    {
        assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/neutral/hud/green_cross.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Animation result = new Animation();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setType(EffectType.Confirm);
        result.setSize(32, 32);
        return result;
    }

    //TODO: Cache
    //TODO: Move map into AnimationSetBuilder
    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations()
    {
        AnimationBuilder animationBuilder = new AnimationBuilder();
        animationBuilder.setTexture(assets.get("data/textures/neutral/hud/green_cross.png", Texture.class));
        animationBuilder.setLayout(effectLayout());

        DirectionalAnimation animation = animationBuilder.build();

        Map<AnimationIdentifier, DirectionalAnimation> result = new HashMap<AnimationIdentifier, DirectionalAnimation>();
        result.put(UnitAnimation.Idle, animation);

        return result;
    }
}
