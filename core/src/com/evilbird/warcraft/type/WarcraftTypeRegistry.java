/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.type;

import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.reflect.BasicTypeRegistry;
import com.evilbird.engine.common.reflect.TypeRegistry;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.common.WarcraftSeason;
import com.evilbird.warcraft.object.common.resource.ResourceType;
import com.evilbird.warcraft.object.data.camera.Camera;
import com.evilbird.warcraft.object.data.camera.CameraType;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerType;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.layer.fog.Fog;
import com.evilbird.warcraft.object.layer.forest.Forest;
import com.evilbird.warcraft.object.layer.terrain.Terrain;
import com.evilbird.warcraft.object.layer.wall.Wall;
import com.evilbird.warcraft.object.projectile.ProjectileType;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitSound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.ResourceExtractor;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;
import com.evilbird.warcraft.object.unit.critter.Critter;
import com.evilbird.warcraft.object.unit.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.resource.Resource;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.state.Level;
import com.evilbird.warcraft.state.WarcraftCampaign;

import javax.inject.Inject;

/**
 * A {@link TypeRegistry} implementation, that binds types used by the Warcraft
 * game to unique identifiers.
 *
 * @author Blair Butterworth
 */
public class WarcraftTypeRegistry extends BasicTypeRegistry
{
    @Inject
    public WarcraftTypeRegistry() {
        registerCommon();
        registerIdentifiers();
        registerItems();
    }

    private void registerCommon() {
        registerType("General", GenericIdentifier.class);
        registerType("Named", TextIdentifier.class);
    }

    private void registerIdentifiers() {
        registerType("AttackActions", AttackActions.class);
        registerType("CameraActions", CameraActions.class);
        registerType("ConfirmActions", ConfirmActions.class);
        registerType("ProduceUnit", ProduceUnitActions.class);
        registerType("MoveActions", MoveActions.class);
        registerType("GatherActions", GatherActions.class);

        registerType("LayerId", LayerIdentifier.class);
        registerType("LayerType", LayerType.class);
        registerType("UnitAnimation", UnitAnimation.class);
        registerType("UnitSound", UnitSound.class);

        registerType("Behaviour", WarcraftBehaviour.class);
        registerType("Faction", WarcraftFaction.class);
        registerType("Assets", WarcraftSeason.class);

        registerType("Level", Level.class);
        registerType("Campaign", WarcraftCampaign.class);
        registerType("Introduction", IntroMenuType.class);
    }

    private void registerItems() {
        registerType("Camera", Camera.class);
        registerType("CameraType", CameraType.class);
        registerType("Player", Player.class);
        registerType("PlayerType", PlayerType.class);
        registerType("Fog", Fog.class);
        registerType("Forest", Forest.class);
        registerType("Terrain", Terrain.class);
        registerType("Wall", Wall.class);
        registerType("Building", Building.class);
        registerType("Units", UnitType.class);
        registerType("Combatant", Combatant.class);
        registerType("RangedCombatant", RangedCombatant.class);
        registerType("Critter", Critter.class);
        registerType("Gatherer", Gatherer.class);
        registerType("Resource", Resource.class);
        registerType("ResourceType", ResourceType.class);
        registerType("ResourceExtractor", ResourceExtractor.class);
        registerType("ProjectileType", ProjectileType.class);
    }
}
