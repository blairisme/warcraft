/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.UnitFaction;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DepositButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.unit.UnitFaction.Human;
import static com.evilbird.warcraft.item.unit.UnitFaction.Orc;
import static com.evilbird.warcraft.item.unit.UnitType.AltarOfStorms;
import static com.evilbird.warcraft.item.unit.UnitType.AnduinLothar;
import static com.evilbird.warcraft.item.unit.UnitType.Ballista;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Battleship;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;
import static com.evilbird.warcraft.item.unit.UnitType.BombardTower;
import static com.evilbird.warcraft.item.unit.UnitType.CannonTower;
import static com.evilbird.warcraft.item.unit.UnitType.Castle;
import static com.evilbird.warcraft.item.unit.UnitType.Catapult;
import static com.evilbird.warcraft.item.unit.UnitType.Chogall;
import static com.evilbird.warcraft.item.unit.UnitType.Church;
import static com.evilbird.warcraft.item.unit.UnitType.Daemon;
import static com.evilbird.warcraft.item.unit.UnitType.DeathKnight;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.Dragon;
import static com.evilbird.warcraft.item.unit.UnitType.DragonRoost;
import static com.evilbird.warcraft.item.unit.UnitType.DwarvenDemolitionSquad;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenRanger;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Ferry;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.Forge;
import static com.evilbird.warcraft.item.unit.UnitType.Fortress;
import static com.evilbird.warcraft.item.unit.UnitType.Foundry;
import static com.evilbird.warcraft.item.unit.UnitType.GiantTurtle;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishFlyingMachine;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishInventor;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishSubmarine;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinAlchemist;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinSappers;
import static com.evilbird.warcraft.item.unit.UnitType.GoblinZeppelin;
import static com.evilbird.warcraft.item.unit.UnitType.GoldMine;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonAviary;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;
import static com.evilbird.warcraft.item.unit.UnitType.GuardTower;
import static com.evilbird.warcraft.item.unit.UnitType.Guldan;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.Knight;
import static com.evilbird.warcraft.item.unit.UnitType.LookoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.Mage;
import static com.evilbird.warcraft.item.unit.UnitType.MageTower;
import static com.evilbird.warcraft.item.unit.UnitType.Metalworks;
import static com.evilbird.warcraft.item.unit.UnitType.Ogre;
import static com.evilbird.warcraft.item.unit.UnitType.OgreJuggernaught;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMage;
import static com.evilbird.warcraft.item.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRefinery;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Paladin;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Refinery;
import static com.evilbird.warcraft.item.unit.UnitType.ScoutTower;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.Stables;
import static com.evilbird.warcraft.item.unit.UnitType.Stronghold;
import static com.evilbird.warcraft.item.unit.UnitType.TempleOfTheDamned;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.Transport;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollBerserker;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static com.evilbird.warcraft.item.unit.UnitType.UtherLightbringer;
import static com.evilbird.warcraft.item.unit.UnitType.WatchTower;
import static com.evilbird.warcraft.item.unit.UnitType.Zuljin;

public class IconSet
{
    private static final GridPoint2 SIZE = new GridPoint2(46, 38);
    private static final int COLUMN_COUNT = 5;

    private Texture texture;
    private Map<Identifier, GridPoint2> layout;

    public IconSet(Texture texture) {
        this.texture = texture;
        this.layout = new HashMap<>();

    }

    public Drawable getIcon(ActionButtonType button, UnitFaction faction) {
//        if (button.isProduceButton()) {
//            return getIcon(button.getProduct());
//        }
        return null;
    }

    public Drawable getIcon(UnitType type) {
        GridPoint2 location = layout.get(type);
        return TextureUtils.getDrawable(texture, location, SIZE);
    }

    public Drawable getIcon(PlayerUpgrade upgrade) {
        GridPoint2 location = layout.get(upgrade);
        return TextureUtils.getDrawable(texture, location, SIZE);
    }

    private void initialize() {
        setLayout(
            Peasant,            Peon,               Footman,                Grunt,                  ElvenArcher,
            TrollAxethrower,    ElvenRanger,        TrollBerserker,         Knight,                 Ogre,
            Paladin,            OgreMage,           DwarvenDemolitionSquad, GoblinSappers,          Mage,
            DeathKnight,        Ballista,           Catapult,               OilTanker,              TrollTanker,
            Transport,          Ferry,              ElvenDestroyer,         TrollDestroyer,         Battleship,
            OgreJuggernaught,   GnomishSubmarine,   GiantTurtle,            GnomishFlyingMachine,   GoblinZeppelin,
            GryphonRider,       Dragon,             AnduinLothar,           Guldan,                 UtherLightbringer,
            Zuljin,             Chogall,            Daemon,                 Farm,                   PigFarm,
            TownHall,           GreatHall,          Barracks,               Encampment,             LumberMill,
            TrollLumberMill,    Blacksmith,         Forge,                  Shipyard,               Dockyard,
            Refinery,           OilRefinery,        Foundry,                Metalworks,             OilPlatform,
            OilRig,             Stables,            OgreMound,              GnomishInventor,        GoblinAlchemist,
            ScoutTower,         WatchTower,         Church,                 AltarOfStorms,          MageTower,
            TempleOfTheDamned,  Keep,               Stronghold,             Castle,                 Fortress,
            Castle,             Fortress,           GryphonAviary,          DragonRoost,            GoldMine,
            GuardTower,         CannonTower,        LookoutTower,           BombardTower,           OilPatch
//            DarkPortal,         CircleOfPower,      Runestone,              HumanMoveButton,        OrcMoveButton,
//            RepairButton,       GatherButton,       BuildSimpleButton,      BuildAdvancedButton,    HumanDepositButton,
//            OrcDepositButton,   CancelButton,       BuildHumanWall,         BuildOrcWall,           SlowButton,
//            InvisibilityButton, HasteButton,        RunesButton,            UnholyArmourButton,     LightningButton,
//            FlameShieldButton,  FireballButton,     TouchOfDarknessButton,  DeathAndDecayButton,    WhirlwindButton,
//            BlizzardButton,     HolyVisionButton,   HealingButton,          DeathCoilButton,        null,
//            ExorcismButton,     EyeOfKilrogg,       BloodlustButton,        null,                   RaiseDeadButton

//            PolymorphButton,    sub(MeleeDamage1, Human), sub(MeleeDamage2, Human), sub(MeleeDamage3, Human), sub(MeleeDamage1, Orc),
//            sub(MeleeDamage2, Orc), sub(MeleeDamage3, Orc), sub(MountedSpeed1, Orc), sub(MountedSpeed2, Orc), sub(RangedDamage1, Human),
//            sub(RangedDamage2, Human), sub(RangedDamage3, Human), sub(RangedDamage1, Orc), sub(RangedDamage2, Orc), sub(RangedDamage3, Orc),
//            sub(MountedSpeed1, Human), sub(MountedSpeed2, Human), sub(RangedAccuracy1, Human), sub(RangedAccuracy2, Human), sub(RangedAccuracy3, Human),
//            sub(RangedAccuracy1, Orc), sub(RangedAccuracy2, Orc), sub(RangedAccuracy3, Orc), sub(SiegeDamage1, Human), sub(SiegeDamage2, Human),
//            sub(SiegeDamage1, Human), sub(SiegeDamage2, Human), sub(DetonateButton, Human), sub(DetonateButton, Orc), sub(CannonDamage1, Human),
        );
    }

    private void setLayout(Identifier ... types) {
        for (int index = 0; index < types.length; ++index) {
            Identifier type = types[index];
            if (type != null) {
                int column = (index % COLUMN_COUNT) * SIZE.x;
                int row = (index / COLUMN_COUNT) * SIZE.y;
                layout.put(type, new GridPoint2(column, row));
            }
        }
    }

    private enum IconSpecialization implements Identifier
    {
        BasicMeleeAttack(),
        ImprovedMeleeAttack(),
        AdvancedMeleeAttack(),

        HumanMeleeAttack(AttackButton, Human),
        HumanMoveButton (MoveButton, Human),

        OrcMoveButton (MoveButton, Orc),
        HumanDepositButton (DepositButton, Human),
        OrcDepositButton (DepositButton, Orc);

        IconSpecialization() {
        }

        IconSpecialization(Identifier primary, Identifier secondary) {
        }
    }
}