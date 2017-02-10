package com.evilbird.warcraft.unit;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.utility.Identifier;

import org.apache.commons.lang3.Range;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO: Apply id and additionl animations to all units.
//TODO: Refactor unit methods into separate classes
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

    public void loadAssets()
    {
        this.assets.load("data/textures/human/perennial/footman.png", Texture.class);
        this.assets.load("data/textures/human/perennial/peasant.png", Texture.class);
        this.assets.load("data/textures/neutral/perennial/construction.png", Texture.class);
        this.assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
        this.assets.load("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        this.assets.load("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        this.assets.load("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        this.assets.load("data/sounds/human/unit/peasant/construct.mp3", Sound.class);
    }

    public Unit newUnit(Identifier type, Identifier id)
    {
        return newUnit(type, id, Collections.<Identifier, DirectionalAnimation>emptyMap());
    }

    public Unit newUnit(Identifier type, Identifier id, Map<Identifier, DirectionalAnimation> additionalAnimations)
    {
        if (Objects.equals(type, FOOTMAN_ID)) return newFootman(id);
        if (Objects.equals(type, PEASANT_ID)) return newPeasant(id);
        if (Objects.equals(type, GRUNT_ID)) return newGrunt(id);
        if (Objects.equals(type, GOLD_MINE_ID)) return newGoldMine(id);
        if (Objects.equals(type, TOWN_HALL_ID)) return newTownHall(id);
        if (Objects.equals(type, BARRACKS_ID)) return newBarracks(id);
        if (Objects.equals(type, FARM_ID)) return newFarm(id);
        if (Objects.equals(type, WOOD_ID)) return newWood(additionalAnimations, id);
        throw new IllegalArgumentException(type.toString());
    }

    private Unit newFootman(Identifier id)
    {
        Texture texture = assets.get("data/textures/human/perennial/footman.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Footman"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Health"), 100f);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newPeasant(Identifier id)
    {
        Sound selected = assets.get("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        Sound complete = assets.get("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        Sound acknowledge = assets.get("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        Sound construct = assets.get("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        Map<Identifier, Sound> sounds = new HashMap<Identifier, Sound>();
        sounds.put(new Identifier("Selected"), selected);
        sounds.put(new Identifier("Complete"), complete);
        sounds.put(new Identifier("Acknowledge"), acknowledge);
        sounds.put(new Identifier("Construct"), construct);

        Texture texture = assets.get("data/textures/human/perennial/peasant.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("GatherWood"), animations.get(new Identifier("Attack")));
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("Build"), animations.get(new Identifier("Hidden")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Peasant"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Health"), 100f);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);
        properties.put(new Identifier("Sounds"), sounds);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newGrunt(Identifier id)
    {
        Texture texture = assets.get("data/textures/orc/perennial/grunt.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getAnimationSet(texture);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Grunt"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Health"), 100f);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newGoldMine(Identifier id)
    {
        Texture texture = assets.get("data/textures/neutral/winter/gold_mine.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 96);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Construct")));
        animations.remove(new Identifier("Construct"));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("GoldMine"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Gold"), 1000f);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newWood(Map<Identifier, DirectionalAnimation> additionalAnimations, Identifier id)
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
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Wood"), 100f);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newTownHall(Identifier id)
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
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newBarracks(Identifier id)
    {
        Texture texture = assets.get("data/textures/human/winter/barracks.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 96);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Barracks"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), false);
        properties.put(new Identifier("Completion"), 0f);
        properties.put(new Identifier("Id"), id);

        return new Unit(properties, animations);
    }

    private Unit newFarm(Identifier id)
    {
        Texture texture = assets.get("data/textures/human/winter/farm.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = getBuildingAnimationSet(texture, 64);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Farm"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), false);
        properties.put(new Identifier("Completion"), 0f);
        properties.put(new Identifier("Id"), id);

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

        Array<TextureRegion> decomposeTextures = getDecomposeAnimation();
        Map<Range<Float>, Array<TextureRegion>> decomposeFrames = new HashMap<Range<Float>, Array<TextureRegion>>(9);
        decomposeFrames.put(Range.between(0f, 360f), decomposeTextures);

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, PlayMode.LOOP);
        DirectionalAnimation moveAnimation = new DirectionalAnimation(0f, 0.15f, moveFrames, PlayMode.LOOP);
        DirectionalAnimation attackAnimation = new DirectionalAnimation(0f, 0.15f, attackFrames, PlayMode.LOOP);
        DirectionalAnimation hiddenAnimation = new DirectionalAnimation(0f, 0.15f, hiddenFrames, PlayMode.LOOP);
        DirectionalAnimation dieAnimation = new DirectionalAnimation(0f, 0.15f, dieFrames, PlayMode.NORMAL);
        DirectionalAnimation decomposeAnimation = new DirectionalAnimation(0f, 2f, decomposeFrames, PlayMode.NORMAL);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Move"), moveAnimation);
        animations.put(new Identifier("Attack"), attackAnimation);
        animations.put(new Identifier("Hidden"), hiddenAnimation);
        animations.put(new Identifier("Die"), dieAnimation);
        animations.put(new Identifier("Decompose"), decomposeAnimation);

        return animations;
    }

    private Map<Identifier, DirectionalAnimation> getBuildingAnimationSet(Texture texture, int size)
    {
        Texture constructionTexture = assets.get("data/textures/neutral/perennial/construction.png", Texture.class);

        TextureRegion constructionRegion1 = new TextureRegion(constructionTexture, 0, 0, 64, 64);
        TextureRegion constructionRegion2 = new TextureRegion(constructionTexture, 0, 64, 64, 64);
        TextureRegion constructionRegion3 = new TextureRegion(texture, 0, size, size, size);

        Array<TextureRegion> idle = getIdleAnimation(texture, 0, 0, size);
        Array<TextureRegion> construction = Array.with(constructionRegion1, constructionRegion2, constructionRegion3);

        Map<Range<Float>, Array<TextureRegion>> idleFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        idleFrames.put(Range.between(0.0f, 360.0f), idle);

        Map<Range<Float>, Array<TextureRegion>> constructionFrames = new HashMap<Range<Float>, Array<TextureRegion>>(1);
        constructionFrames.put(Range.between(0.0f, 360.0f), construction);

        DirectionalAnimation idleAnimation = new DirectionalAnimation(0f, 0.15f, idleFrames, PlayMode.LOOP);
        DirectionalAnimation constructionAnimation = new DirectionalAnimation(0f, 3f, constructionFrames, PlayMode.NORMAL);

        Map<Identifier, DirectionalAnimation> animations = new HashMap<Identifier, DirectionalAnimation>();
        animations.put(new Identifier("Idle"), idleAnimation);
        animations.put(new Identifier("Construct"), constructionAnimation);

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
        return Array.with(region1, region2, region3, region4);
    }

    private Array<TextureRegion> getDeadAnimation(Texture texture, int x)
    {
        TextureRegion region1 = new TextureRegion(texture, x, 648, 72, 72);
        TextureRegion region2 = new TextureRegion(texture, x, 720, 72, 72);
        TextureRegion region3 = new TextureRegion(texture, x, 792, 72, 72);
        TextureRegion region4 = new TextureRegion(texture, 0, 0, 1, 1);
        return Array.with(region1, region2, region3, region4);
    }

    private Array<TextureRegion> getDecomposeAnimation()
    {
        Texture corpseTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        TextureRegion region1 = new TextureRegion(corpseTexture, 0, 0, 72, 72);
        TextureRegion region2 = new TextureRegion(corpseTexture, 0, 72, 72, 72);
        TextureRegion region3 = new TextureRegion(corpseTexture, 0, 144, 72, 72);
        TextureRegion region4 = new TextureRegion(corpseTexture, 0, 216, 72, 72);
        TextureRegion region5 = new TextureRegion(corpseTexture, 0, 288, 72, 72);
        TextureRegion region6 = new TextureRegion(corpseTexture, 0, 360, 72, 72);
        return Array.with(region1, region2, region3, region4, region5, region6);
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
