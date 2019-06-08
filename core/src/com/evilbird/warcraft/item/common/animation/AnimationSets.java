/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.animation;

import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.graphics.Animation;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Map;

import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.buildingDestructionScheme;
import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.gatheringSchema;
import static com.evilbird.warcraft.item.common.animation.AnimationSchemas.idleSingularSchema;

/**
 * Used to obtain commonly used animation sequence sets. For example all of the
 * animations common to combatants.
 *
 * @author Blair Butterworth
 */
public class AnimationSets
{
    private AnimationSets() {
    }

    public static Map<Identifier, Animation> combatantAnimations(
            Texture generalTexture, Texture decomposeTexture)
    {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSchema(), generalTexture);
        builder.set(UnitAnimation.Move, AnimationSchemas.moveSchema(), generalTexture);
        builder.set(UnitAnimation.MeleeAttack, AnimationSchemas.attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, AnimationSchemas.hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Death, AnimationSchemas.deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decomposeTexture);
        return builder.build();
    }

    public static Map<Identifier, Animation> gatherAnimations(
            Texture generalTexture, Texture decomposeTexture, Texture moveGoldTexture, Texture moveWoodTexture)
    {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.IdleBasic, AnimationSchemas.idleSchema(), generalTexture);
        builder.set(UnitAnimation.IdleGold, AnimationSchemas.idleSchema(), moveGoldTexture);
        builder.set(UnitAnimation.IdleWood, AnimationSchemas.idleSchema(), moveWoodTexture);
        builder.associate(UnitAnimation.Idle, UnitAnimation.IdleBasic);
        builder.set(UnitAnimation.MoveBasic, AnimationSchemas.moveSchema(), generalTexture);
        builder.set(UnitAnimation.MoveGold, AnimationSchemas.moveSchema(), moveGoldTexture);
        builder.set(UnitAnimation.MoveWood, AnimationSchemas.moveSchema(), moveWoodTexture);
        builder.associate(UnitAnimation.Move, UnitAnimation.MoveBasic);
        builder.set(UnitAnimation.MeleeAttack, AnimationSchemas.attackSchema(), generalTexture);
        builder.set(UnitAnimation.Hidden, AnimationSchemas.hiddenSchema(), generalTexture);
        builder.set(UnitAnimation.Death, AnimationSchemas.deathSchema(), generalTexture);
        builder.set(UnitAnimation.Decompose, AnimationSchemas.decomposeSchema(), decomposeTexture);
        builder.associate(UnitAnimation.GatherGold, UnitAnimation.Hidden);
        builder.associate(UnitAnimation.GatherWood, UnitAnimation.MeleeAttack);
        builder.associate(UnitAnimation.Build, UnitAnimation.Hidden);
        return builder.build();
    }

    public static Map<Identifier, Animation> effectAnimations(Texture texture) {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.effectSchema(), texture);
        return builder.build();
    }

    public static Map<Identifier, Animation> buildingAnimations(
            Texture general, Texture construction, Texture destruction, int width, int height)
    {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, AnimationSchemas.idleSingularSchema(width, height), general);
        builder.set(UnitAnimation.BuildingSite, AnimationSchemas.constructStaticSchema(width, height), construction);
        builder.set(UnitAnimation.Construct, Arrays.asList(
                Pair.of(AnimationSchemas.constructBeginSchema(width, height), construction),
                Pair.of(AnimationSchemas.constructEndSchema(width, height), general)));
        builder.set(UnitAnimation.Dead, buildingDestructionScheme(), destruction);
        return builder.build();
    }

    public static Map<Identifier, Animation> resourceBuildingAnimations(
            Texture general, Texture destruction)
    {
        AnimationSetBuilder builder = new AnimationSetBuilder();
        builder.set(UnitAnimation.Idle, idleSingularSchema(96, 96), general);
        builder.set(UnitAnimation.Gathering, gatheringSchema(96, 96), general);
        builder.set(UnitAnimation.Dead, buildingDestructionScheme(), destruction);
        return builder.build();
    }
}
