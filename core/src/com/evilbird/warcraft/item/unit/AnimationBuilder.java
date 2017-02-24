package com.evilbird.warcraft.item.unit;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.utility.Identifier;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;

//TODO: Needs cleaning/refactoring
public class AnimationBuilder
{
    public static Map<Identifier, DirectionalAnimation> getAnimationSet(Texture texture, Texture decomposeTexture)
    {
        Array<TextureRegion> walkNorth = getWalkAnimation(texture, 0);
        Array<TextureRegion> walkNorthEast = getWalkAnimation(texture, 72);
        Array<TextureRegion> walkEast = getWalkAnimation(texture, 144);
        Array<TextureRegion> walkSouthEast = getWalkAnimation(texture, 216);
        Array<TextureRegion> walkSouth = getWalkAnimation(texture, 288);
        Array<TextureRegion> walkSouthWest = getWalkAnimation(texture, 360);
        Array<TextureRegion> walkWest = getWalkAnimation(texture, 432);
        Array<TextureRegion> walkNorthWest = getWalkAnimation(texture, 504);

        Map<Range<Float>, Array<TextureRegion>> moveFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        moveFrames.put(Range.between(112.5f, 67.5f), walkNorth);
        moveFrames.put(Range.between(67.5f, 22.5f), walkNorthEast);
        moveFrames.put(Range.between(22.5f, 0.0f), walkEast);
        moveFrames.put(Range.between(360.0f, 337.5f), walkEast);
        moveFrames.put(Range.between(337.5f, 292.5f), walkSouthEast);
        moveFrames.put(Range.between(292.5f, 247.5f), walkSouth);
        moveFrames.put(Range.between(247.5f, 202.5f), walkSouthWest);
        moveFrames.put(Range.between(202.5f, 157.5f), walkWest);
        moveFrames.put(Range.between(157.5f, 112.5f), walkNorthWest);

        Array<TextureRegion> attackNorth = getAttackAnimation(texture, 0);
        Array<TextureRegion> attackNorthEast = getAttackAnimation(texture, 72);
        Array<TextureRegion> attackEast = getAttackAnimation(texture, 144);
        Array<TextureRegion> attackSouthEast = getAttackAnimation(texture, 216);
        Array<TextureRegion> attackSouth = getAttackAnimation(texture, 288);
        Array<TextureRegion> attackSouthWest = getAttackAnimation(texture, 360);
        Array<TextureRegion> attackWest = getAttackAnimation(texture, 432);
        Array<TextureRegion> attackNorthWest = getAttackAnimation(texture, 504);

        Map<Range<Float>, Array<TextureRegion>> attackFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        attackFrames.put(Range.between(112.5f, 67.5f), attackNorth);
        attackFrames.put(Range.between(67.5f, 22.5f), attackNorthEast);
        attackFrames.put(Range.between(22.5f, 0.0f), attackEast);
        attackFrames.put(Range.between(360.0f, 337.5f), attackEast);
        attackFrames.put(Range.between(337.5f, 292.5f), attackSouthEast);
        attackFrames.put(Range.between(292.5f, 247.5f), attackSouth);
        attackFrames.put(Range.between(247.5f, 202.5f), attackSouthWest);
        attackFrames.put(Range.between(202.5f, 157.5f), attackWest);
        attackFrames.put(Range.between(157.5f, 112.5f), attackNorthWest);

        Array<TextureRegion> deadNorth = getDeadAnimation(texture, 0);
        Array<TextureRegion> deadNorthEast = getDeadAnimation(texture, 72);
        Array<TextureRegion> deadEast = getDeadAnimation(texture, 144);
        Array<TextureRegion> deadSouthEast = getDeadAnimation(texture, 216);
        Array<TextureRegion> deadSouth = getDeadAnimation(texture, 288);
        Array<TextureRegion> deadSouthWest = getDeadAnimation(texture, 360);
        Array<TextureRegion> deadWest = getDeadAnimation(texture, 432);
        Array<TextureRegion> deadNorthWest = getDeadAnimation(texture, 504);

        Map<Range<Float>, Array<TextureRegion>> dieFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        dieFrames.put(Range.between(112.5f, 67.5f), deadNorth);
        dieFrames.put(Range.between(67.5f, 22.5f), deadNorthEast);
        dieFrames.put(Range.between(22.5f, 0.0f), deadEast);
        dieFrames.put(Range.between(360.0f, 337.5f), deadEast);
        dieFrames.put(Range.between(337.5f, 292.5f), deadSouthEast);
        dieFrames.put(Range.between(292.5f, 247.5f), deadSouth);
        dieFrames.put(Range.between(247.5f, 202.5f), deadSouthWest);
        dieFrames.put(Range.between(202.5f, 157.5f), deadWest);
        dieFrames.put(Range.between(157.5f, 112.5f), deadNorthWest);

        Array<TextureRegion> idleNorth = getIdleAnimation(texture, 0, 72);
        Array<TextureRegion> idleNorthEast = getIdleAnimation(texture, 72, 72);
        Array<TextureRegion> idleEast = getIdleAnimation(texture, 144, 72);
        Array<TextureRegion> idleSouthEast = getIdleAnimation(texture, 216, 72);
        Array<TextureRegion> idleSouth = getIdleAnimation(texture, 288, 72);
        Array<TextureRegion> idleSouthWest = getIdleAnimation(texture, 360, 72);
        Array<TextureRegion> idleWest = getIdleAnimation(texture, 432, 72);
        Array<TextureRegion> idleNorthWest = getIdleAnimation(texture, 504, 72);

        Map<Range<Float>, Array<TextureRegion>> idleFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        idleFrames.put(Range.between(112.5f, 67.5f), idleNorth);
        idleFrames.put(Range.between(67.5f, 22.5f), idleNorthEast);
        idleFrames.put(Range.between(22.5f, 0.0f), idleEast);
        idleFrames.put(Range.between(360.0f, 337.5f), idleEast);
        idleFrames.put(Range.between(337.5f, 292.5f), idleSouthEast);
        idleFrames.put(Range.between(292.5f, 247.5f), idleSouth);
        idleFrames.put(Range.between(247.5f, 202.5f), idleSouthWest);
        idleFrames.put(Range.between(202.5f, 157.5f), idleWest);
        idleFrames.put(Range.between(157.5f, 112.5f), idleNorthWest);

        Array<TextureRegion> hiddenFrame = getIdleAnimation(texture, 0, 1);
        Map<Range<Float>, Array<TextureRegion>> hiddenFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        hiddenFrames.put(Range.between(0f, 360f), hiddenFrame);

        Array<TextureRegion> decomposeTextures = getDecomposeAnimation(decomposeTexture);
        Map<Range<Float>, Array<TextureRegion>> decomposeFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        decomposeFrames.put(Range.between(0f, 360f), decomposeTextures);

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, Animation.PlayMode.LOOP);
        DirectionalAnimation moveAnimation = new DirectionalAnimation(0f, 0.15f, moveFrames, Animation.PlayMode.LOOP);
        DirectionalAnimation attackAnimation = new DirectionalAnimation(0f, 0.15f, attackFrames, Animation.PlayMode.LOOP);
        DirectionalAnimation hiddenAnimation = new DirectionalAnimation(0f, 0.15f, hiddenFrames, Animation.PlayMode.LOOP);
        DirectionalAnimation dieAnimation = new DirectionalAnimation(0f, 0.15f, dieFrames, Animation.PlayMode.NORMAL);
        DirectionalAnimation decomposeAnimation = new DirectionalAnimation(0f, 2f, decomposeFrames, Animation.PlayMode.NORMAL);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Move"), moveAnimation);
        animations.put(new Identifier("Attack"), attackAnimation);
        animations.put(new Identifier("Hidden"), hiddenAnimation);
        animations.put(new Identifier("Die"), dieAnimation);
        animations.put(new Identifier("Decompose"), decomposeAnimation);

        return animations;
    }

    public static Map<Identifier, DirectionalAnimation> getBuildingAnimationSet(Texture texture, Texture constructionTexture, int size)
    {
        TextureRegion constructionRegion1 = new TextureRegion(constructionTexture, 0, 0, 64, 64);
        TextureRegion constructionRegion2 = new TextureRegion(constructionTexture, 0, 64, 64, 64);
        TextureRegion constructionRegion3 = new TextureRegion(texture, 0, size, size, size);

        Array<TextureRegion> idle = getAnimation(texture, 0, 0, size);
        Array<TextureRegion> construction = Array.with(constructionRegion1, constructionRegion2, constructionRegion3);

        Map<Range<Float>, Array<TextureRegion>> idleFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        idleFrames.put(Range.between(0.0f, 360.0f), idle);

        Map<Range<Float>, Array<TextureRegion>> constructionFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        constructionFrames.put(Range.between(0.0f, 360.0f), construction);

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, Animation.PlayMode.LOOP);
        DirectionalAnimation constructionAnimation = new DirectionalAnimation(0f, 3f, constructionFrames, Animation.PlayMode.NORMAL);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Construct"), constructionAnimation);

        return animations;
    }

    private static Array<TextureRegion> getWalkAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 0, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 72, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 144, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, x, 216, 72, 72);
        TextureRegion region5 = new TextureRegion(texture, x, 288, 72, 72);
        return Array.with(region1, region2, region3, region4, region5);
    }

    private static Array<TextureRegion> getAttackAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 360, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 432, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 504, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, x, 576, 72, 72);
        return Array.with(region1, region2, region3, region4);
    }

    private static Array<TextureRegion> getDeadAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 648, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 720, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 792, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, 0, 0, 1, 1);
        return Array.with(region1, region2, region3, region4);
    }

    private static Array<TextureRegion> getDecomposeAnimation(Texture decomposeTexture)
    {
        TextureRegion region1 = new TextureRegion(decomposeTexture, 0, 0, 72, 72);
        TextureRegion region2 = new TextureRegion(decomposeTexture, 0, 72, 72, 72);
        TextureRegion region3 = new TextureRegion(decomposeTexture, 0, 144, 72, 72);
        TextureRegion region4 = new TextureRegion(decomposeTexture, 0, 216, 72, 72);
        TextureRegion region5 = new TextureRegion(decomposeTexture, 0, 288, 72, 72);
        TextureRegion region6 = new TextureRegion(decomposeTexture, 0, 360, 72, 72);
        return Array.with(region1, region2, region3, region4, region5, region6);
    }

    private static Array<TextureRegion> getIdleAnimation(Texture texture, int x, int size)
    {
        return getAnimation(texture, x, 0, size);
    }

    public static Array<TextureRegion> getAnimation(Texture texture, int x, int y, int size)
    {
        TextureRegion region1 = new TextureRegion(texture, x, y, size, size);
        return Array.with(region1);
    }
}
