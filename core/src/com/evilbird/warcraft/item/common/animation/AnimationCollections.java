package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by blair on 10/09/2017.
 */
public class AnimationCollections
{
    public static Map<AnimationIdentifier, DirectionalAnimation> combatantAnimations(
            Texture generalTexture, Texture decomposeTexture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSchema(), generalTexture);
        builder.set(UnitAnimation.Move, AnimationSchemas.moveSchema(), generalTexture);
        builder.set(UnitAnimation.Attack, AnimationSchemas.attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, AnimationSchemas.hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Die, AnimationSchemas.deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decomposeTexture);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> gatherAnimations(
            Texture generalTexture, Texture decomposeTexture, Texture moveGoldTexture, Texture moveWoodTexture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.IdleBasic, AnimationSchemas.idleSchema(), generalTexture);
        builder.set(UnitAnimation.IdleGold, AnimationSchemas.idleSchema(), moveGoldTexture);
        builder.set(UnitAnimation.IdleWood, AnimationSchemas.idleSchema(), moveWoodTexture);
        builder.associate(UnitAnimation.Idle, UnitAnimation.IdleBasic);
        builder.set(UnitAnimation.MoveBasic, AnimationSchemas.moveSchema(), generalTexture);
        builder.set(UnitAnimation.MoveGold, AnimationSchemas.moveSchema(), moveGoldTexture);
        builder.set(UnitAnimation.MoveWood, AnimationSchemas.moveSchema(), moveWoodTexture);
        builder.associate(UnitAnimation.Move, UnitAnimation.MoveBasic);
        builder.set(UnitAnimation.Attack, AnimationSchemas.attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, AnimationSchemas.hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Die, AnimationSchemas.deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decomposeTexture);
        builder.associate(UnitAnimation.GatherWood, UnitAnimation.Attack);
        builder.associate(UnitAnimation.Build, UnitAnimation.Hidden);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> effectAnimations(
            Texture texture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.effectSchema(), texture);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> buildingAnimations(
            Texture general, Texture construct, int width, int height)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSingualarSchema(width, height), general);
        builder.set(UnitAnimation.Construct, Arrays.asList(
                Pair.of(AnimationSchemas.constructBeginSchema(width, height), construct),
                Pair.of(AnimationSchemas.constructEndSchema(width, height), general)));
        builder.associate(UnitAnimation.DepositGold, UnitAnimation.Idle);
        builder.associate(UnitAnimation.DepositWood, UnitAnimation.Idle);
        return builder.build();
    }
}
