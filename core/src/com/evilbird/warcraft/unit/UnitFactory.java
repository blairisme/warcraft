package com.evilbird.warcraft.unit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.warcraft.graphics.DirectionalAnimation;
import com.evilbird.warcraft.utility.Identifier;

import org.apache.commons.lang3.Range;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UnitFactory
{
    private static final Identifier FOOTMAN_ID = new Identifier("Footman");
    private static final Identifier PEASANT_ID = new Identifier("Peasant");
    private static final Identifier GRUNT_ID = new Identifier("Grunt");
    private static final Identifier GOLD_MINE_ID = new Identifier("GoldMine");
    private static final Identifier TOWN_HALL_ID = new Identifier("TownHall");
    private static final Identifier BARRACKS_ID = new Identifier("Barracks");
    private static final Identifier FARM_ID = new Identifier("Farm");
    private static final Identifier WOOD_ID = new Identifier("Wood");

    private AssetManager assets;

    public UnitFactory(AssetManager assets)
    {
        this.assets = assets;
    }

    public Unit newUnit(Identifier identifier)
    {
        if (Objects.equals(identifier, FOOTMAN_ID)) return newFootman();
        if (Objects.equals(identifier, PEASANT_ID)) return newPeasant();
        if (Objects.equals(identifier, GRUNT_ID)) return newGrunt();
        if (Objects.equals(identifier, GOLD_MINE_ID)) return newGoldMine();
        if (Objects.equals(identifier, TOWN_HALL_ID)) return newTownHall();
        if (Objects.equals(identifier, BARRACKS_ID)) return newBarracks();
        if (Objects.equals(identifier, FARM_ID)) return newFarm();
        throw new IllegalArgumentException();
    }

    //TODO: Combine with other newUnit method
    public Unit newUnit(Identifier identifier, Map<Identifier, DirectionalAnimation> additionalAnimations)
    {
        if (Objects.equals(identifier, WOOD_ID)) return newWood(additionalAnimations);
        throw new IllegalArgumentException();
    }

    private Unit newFootman()
    {
        Texture texture = assets.get("data/textures/human/perennial/footman.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Footman"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);

        return new Unit(properties, animations);
    }

    private Unit newPeasant()
    {
        Texture texture = assets.get("data/textures/human/perennial/peasant.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("GatherWood"), animations.get(new Identifier("Attack")));
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Hidden")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Peasant"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);

        return new Unit(properties, animations);
    }

    private Unit newGrunt()
    {
        Texture texture = assets.get("data/textures/orc/perennial/grunt.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Grunt"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);

        return new Unit(properties, animations);
    }

    private Unit newGoldMine()
    {
        Texture texture = assets.get("data/textures/neutral/winter/gold_mine.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 96);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Busy")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("GoldMine"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 1000f);

        return new Unit(properties, animations);
    }

    private Unit newWood(Map<Identifier, DirectionalAnimation> additionalAnimations)
    {
        Texture texture = assets.get("data/textures/neutral/winter/terrain.png", Texture.class);
        Array<TextureRegion> deathTextures = getIdleAnimation(texture, 15 * 32, 7 * 32, 32);
        Map<Range<Float>, Array<TextureRegion>> deathFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        deathFrames.put(Range.between(0.0f, 360.0f), deathTextures);
        DirectionalAnimation deathAnimation = new DirectionalAnimation(0f, 0.15f, deathFrames, PlayMode.LOOP);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.putAll(additionalAnimations);
        animations.put(new Identifier("Dead"), deathAnimation);
        animations.put(new Identifier("GatherWood"), animations.get(new Identifier("Idle")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Wood"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Wood"), 100f);

        return new Unit(properties, animations);
    }

    private Unit newTownHall()
    {
        Texture texture = assets.get("data/textures/human/winter/town_hall.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 128);
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Idle")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Idle")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("TownHall"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);

        return new Unit(properties, animations);
    }

    private Unit newBarracks()
    {
        Texture texture = assets.get("data/textures/human/winter/barracks.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 96);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Barracks"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);

        return new Unit(properties, animations);
    }

    private Unit newFarm()
    {
        Texture texture = assets.get("data/textures/human/winter/farm.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 64);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Farm"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);

        return new Unit(properties, animations);
    }

    private Map<Identifier, DirectionalAnimation> getAnimationSet(Texture texture)
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

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, PlayMode.LOOP);
        DirectionalAnimation moveAnimation = new DirectionalAnimation(0f, 0.15f, moveFrames, PlayMode.LOOP);
        DirectionalAnimation attackAnimation = new DirectionalAnimation(0f, 0.15f, attackFrames, PlayMode.LOOP);
        DirectionalAnimation hiddenAnimation = new DirectionalAnimation(0f, 0.15f, hiddenFrames, PlayMode.LOOP);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Move"), moveAnimation);
        animations.put(new Identifier("Attack"), attackAnimation);
        animations.put(new Identifier("Hidden"), hiddenAnimation);

        return animations;
    }

    private Map<Identifier, DirectionalAnimation> getBuildingAnimationSet(Texture texture, int size)
    {
        Array<TextureRegion> idle = getIdleAnimation(texture, 0, 0, size);
        Array<TextureRegion> busy = getIdleAnimation(texture, 0, size, size);

        Map<Range<Float>, Array<TextureRegion>> idleFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        idleFrames.put(Range.between(0.0f, 360.0f), idle);

        Map<Range<Float>, Array<TextureRegion>> busyFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        busyFrames.put(Range.between(0.0f, 360.0f), busy);

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, PlayMode.LOOP);
        DirectionalAnimation busyAnimation = new DirectionalAnimation(0f, 0.15f, busyFrames, PlayMode.LOOP);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Busy"), busyAnimation);

        return animations;
    }

    private Array<TextureRegion> getWalkAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 0, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 72, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 144, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, x, 216, 72, 72);
        TextureRegion region5 = new TextureRegion(texture, x, 288, 72, 72);
        return Array.with(region1, region2, region3, region4, region5);
    }

    private Array<TextureRegion> getAttackAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 360, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 432, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 504, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, x, 576, 72, 72);
        TextureRegion region5 = new TextureRegion(texture, x, 648, 72, 72);
        return Array.with(region1, region2, region3, region4, region5);
    }

    private Array<TextureRegion> getIdleAnimation(Texture texture, int x, int size)
    {
        return getIdleAnimation(texture, x, 0, size);
    }

    private Array<TextureRegion> getIdleAnimation(Texture texture, int x, int y, int size)
    {
        TextureRegion region1 = new TextureRegion(texture, x, y, size, size);
        return Array.with(region1);
    }
}
