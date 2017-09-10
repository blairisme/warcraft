package com.evilbird.warcraft.common;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;

import java.util.Map;

import static com.evilbird.warcraft.common.AnimationSchemas.attackSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.constuctSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.deathSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.decomposeSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.effectSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.hiddenSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.idleSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.idleSingualarSchema;
import static com.evilbird.warcraft.common.AnimationSchemas.moveSchema;

/**
 * Created by blair on 10/09/2017.
 */
public class AnimationCollections
{
    public static Map<AnimationIdentifier, DirectionalAnimation> combatantAnimations(
            Texture generalTexture, Texture decomposeTexture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, idleSchema(), generalTexture);
        builder.set(UnitAnimation.Move, moveSchema(), generalTexture);
        builder.set(UnitAnimation.Attack, attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Die, deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, decomposeSchema(), decomposeTexture);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> gatherAnimations(
            Texture generalTexture, Texture decomposeTexture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, idleSchema(), generalTexture);
        builder.set(UnitAnimation.Move, moveSchema(), generalTexture);
        builder.set(UnitAnimation.Attack, attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Die, deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, decomposeSchema(), decomposeTexture);
        builder.associate(UnitAnimation.GatherGold, UnitAnimation.Hidden);
        builder.associate(UnitAnimation.GatherWood, UnitAnimation.Attack);
        builder.associate(UnitAnimation.DepositGold, UnitAnimation.Hidden);
        builder.associate(UnitAnimation.DepositWood, UnitAnimation.Hidden);
        builder.associate(UnitAnimation.Build, UnitAnimation.Hidden);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> effectAnimations(
            Texture texture)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, effectSchema(), texture);
        return builder.build();
    }

    public static Map<AnimationIdentifier, DirectionalAnimation> buildingAnimations(
            Texture general, Texture construct, int width, int height)
    {
        AnimationCollectionBuilder builder = new AnimationCollectionBuilder();
        builder.set(UnitAnimation.Idle, idleSingualarSchema(width, height), general);
        builder.set(UnitAnimation.Construct, constuctSchema(width, height), construct);
        builder.merge(UnitAnimation.Construct, UnitAnimation.Idle);
        builder.associate(UnitAnimation.DepositGold, UnitAnimation.Idle);
        builder.associate(UnitAnimation.DepositWood, UnitAnimation.Idle);
        return builder.build();
    }
}
