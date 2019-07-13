package com.evilbird.warcraft.type;

import com.evilbird.engine.common.lang.GenericIdentifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.common.reflect.BasicTypeRegistry;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.camera.CameraActions;
import com.evilbird.warcraft.action.confirm.ConfirmActions;
import com.evilbird.warcraft.action.move.MoveActions;
import com.evilbird.warcraft.action.produce.ProduceUnitActions;
import com.evilbird.warcraft.behaviour.WarcraftBehaviour;
import com.evilbird.warcraft.common.WarcraftAssetSet;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.camera.Camera;
import com.evilbird.warcraft.item.data.camera.CameraType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerType;
import com.evilbird.warcraft.item.layer.LayerIdentifier;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.layer.fog.Fog;
import com.evilbird.warcraft.item.layer.forest.Forest;
import com.evilbird.warcraft.item.layer.terrain.Terrain;
import com.evilbird.warcraft.item.layer.wall.Wall;
import com.evilbird.warcraft.item.ui.hud.HudType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.state.campaign.Campaign;
import com.evilbird.warcraft.state.map.Level;

import javax.inject.Inject;

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
        registerType("Hud", HudType.class);
        registerType("LayerId", LayerIdentifier.class);
        registerType("LayerType", LayerType.class);
        registerType("UnitAnimation", UnitAnimation.class);
        registerType("UnitSound", UnitSound.class);
        registerType("Units", UnitType.class);
        registerType("ResourceType", ResourceType.class);
        registerType("Behaviour", WarcraftBehaviour.class);

        //registerType("Context", WarcraftGameContext.class);
        registerType("Faction", WarcraftFaction.class);
        registerType("Assets", WarcraftAssetSet.class);
        registerType("Level", Level.class);

        registerType("Campaign", Campaign.class);
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
        registerType("Combatant", Combatant.class);
        registerType("Gatherer", Gatherer.class);
        registerType("Resource", Resource.class);
    }
}
