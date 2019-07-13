/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.unit.UnitAttack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage1;
import static com.evilbird.warcraft.item.data.player.PlayerUpgrade.RangedDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedMeleeUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AdvancedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildBarracksButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildDockyardButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildEncampmentButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildGreatHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildLumberMillButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOilPlatformButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildOilRigButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildPigFarmButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildShipyardButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTownHallButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.BuildTrollLumberMillButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DefendButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DepositButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DetonateButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.DisembarkButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.GatherButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedMeleeUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.ImprovedRangedUpgradeButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.PatrolButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.PolymorphButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.StopButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainElvenArcherButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainElvenDestroyerButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainFootmanButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainGruntButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainOilTankerButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainPeasantButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainPeonButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainTrollAxethrowerButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainTrollDestroyerButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.TrainTrollTankerButton;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconLevel.Advanced;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconLevel.Basic;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconLevel.Improved;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconSpecialization.special;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.Any;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanArmourPlating1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanArmourPlating2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanDefend;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanDeposit;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanDetonate;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanDisembark;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMeleeAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMeleeDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMeleeDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMeleeDefence1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMeleeDefence2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanMove;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanPatrol;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanRangedAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanRangedDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanRangedDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipDeposit;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipGather;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipMove;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanShipStop;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanSiegeAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanSiegeDamage;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.HumanStop;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcArmourPlating1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcArmourPlating2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcDefend;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcDeposit;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcDetonate;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcDisembark;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMeleeAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMeleeDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMeleeDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMeleeDefence1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMeleeDefence2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcMove;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcPatrol;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcRangedAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcRangedDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcRangedDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipDamage1;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipDamage2;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipDeposit;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipGather;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipMove;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcShipStop;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcSiegeAttack;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcSiegeDamage;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconType.OrcStop;
import static com.evilbird.warcraft.item.unit.UnitAttack.Melee;
import static com.evilbird.warcraft.item.unit.UnitAttack.Ranged;
import static com.evilbird.warcraft.item.unit.UnitAttack.Ship;
import static com.evilbird.warcraft.item.unit.UnitAttack.Siege;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Boar;
import static com.evilbird.warcraft.item.unit.UnitType.Dockyard;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.Encampment;
import static com.evilbird.warcraft.item.unit.UnitType.Farm;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static com.evilbird.warcraft.item.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.item.unit.UnitType.Grunt;
import static com.evilbird.warcraft.item.unit.UnitType.LumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.item.unit.UnitType.OilRig;
import static com.evilbird.warcraft.item.unit.UnitType.OilTanker;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static com.evilbird.warcraft.item.unit.UnitType.Peon;
import static com.evilbird.warcraft.item.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.item.unit.UnitType.Seal;
import static com.evilbird.warcraft.item.unit.UnitType.Sheep;
import static com.evilbird.warcraft.item.unit.UnitType.Shipyard;
import static com.evilbird.warcraft.item.unit.UnitType.TownHall;
import static com.evilbird.warcraft.item.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.item.unit.UnitType.TrollDestroyer;
import static com.evilbird.warcraft.item.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.item.unit.UnitType.TrollTanker;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Defines icon specializations, icons that differ when displayed for a
 * specific faction, attack capability or player upgrade, or a combination of
 * these.
 *
 * @author Blair Butterworth
 */
public class IconSpecializations
{
    private static Map<Identifier, Identifier> specializations = specializations(
        Boar, PolymorphButton,
        Seal, PolymorphButton,
        Sheep, PolymorphButton,

        BuildCancelButton, CancelButton,

        ImprovedMeleeUpgradeButton, MeleeDamage1,
        AdvancedMeleeUpgradeButton, MeleeDamage2,
        ImprovedRangedUpgradeButton, RangedDamage1,
        AdvancedRangedUpgradeButton, RangedDamage2,

        BuildBarracksButton, Barracks,
        BuildFarmButton, Farm,
        BuildLumberMillButton, LumberMill,
        BuildOilPlatformButton, OilPlatform,
        BuildShipyardButton, Shipyard,
        BuildTownHallButton, TownHall,

        BuildDockyardButton, Dockyard,
        BuildEncampmentButton, Encampment,
        BuildGreatHallButton, GreatHall,
        BuildOilRigButton, OilRig,
        BuildPigFarmButton, PigFarm,
        BuildTrollLumberMillButton, TrollLumberMill,

        TrainElvenArcherButton, ElvenArcher,
        TrainElvenDestroyerButton, ElvenDestroyer,
        TrainFootmanButton, Footman,
        TrainOilTankerButton, OilTanker,
        TrainPeasantButton, Peasant,

        TrainGruntButton, Grunt,
        TrainPeonButton, Peon,
        TrainTrollAxethrowerButton, TrollAxethrower,
        TrainTrollDestroyerButton, TrollDestroyer,
        TrainTrollTankerButton, TrollTanker,

        special(AttackButton, Human, Melee, Basic), HumanMeleeAttack,
        special(AttackButton, Human, Melee, Improved), HumanMeleeDamage1,
        special(AttackButton, Human, Melee, Advanced), HumanMeleeDamage2,
        special(AttackButton, Human, Ranged, Basic), HumanRangedAttack,
        special(AttackButton, Human, Ranged, Improved), HumanRangedDamage1,
        special(AttackButton, Human, Ranged, Advanced), HumanRangedDamage2,
        special(AttackButton, Human, Ship, Basic), HumanShipAttack,
        special(AttackButton, Human, Ship, Improved), HumanShipDamage1,
        special(AttackButton, Human, Ship, Advanced), HumanShipDamage2,
        special(AttackButton, Human, Siege, Basic), HumanSiegeAttack,
        special(AttackButton, Human, Siege, Improved), HumanSiegeDamage,
        special(AttackButton, Orc, Melee, Basic), OrcMeleeAttack,
        special(AttackButton, Orc, Melee, Improved), OrcMeleeDamage1,
        special(AttackButton, Orc, Melee, Advanced), OrcMeleeDamage2,
        special(AttackButton, Orc, Ranged, Basic), OrcRangedAttack,
        special(AttackButton, Orc, Ranged, Improved), OrcRangedDamage1,
        special(AttackButton, Orc, Ranged, Advanced), OrcRangedDamage2,
        special(AttackButton, Orc, Ship, Basic), OrcShipAttack,
        special(AttackButton, Orc, Ship, Improved), OrcShipDamage1,
        special(AttackButton, Orc, Ship, Advanced), OrcShipDamage2,
        special(AttackButton, Orc, Siege, Basic), OrcSiegeAttack,
        special(AttackButton, Orc, Siege, Improved), OrcSiegeDamage,

        special(DepositButton, Human, Any, Any), HumanDeposit,
        special(DepositButton, Human, Ship, Any), HumanShipDeposit,
        special(DepositButton, Orc, Any, Any), OrcDeposit,
        special(DepositButton, Orc, Ship, Any), OrcShipDeposit,

        special(DefendButton, Human, Any, Any), HumanDefend,
        special(DefendButton, Orc, Any, Any), OrcDefend,

        special(DetonateButton, Human, Any, Any), HumanDetonate,
        special(DetonateButton, Orc, Any, Any), OrcDetonate,

        special(DisembarkButton, Human, Any, Any), HumanDisembark,
        special(DisembarkButton, Orc, Any, Any), OrcDisembark,

        special(GatherButton, Human, Ship, Any), HumanShipGather,
        special(GatherButton, Orc, Ship, Any), OrcShipGather,

        special(MoveButton, Human, Any, Any), HumanMove,
        special(MoveButton, Human, Ship, Any), HumanShipMove,
        special(MoveButton, Orc, Any, Any), OrcMove,
        special(MoveButton, Orc, Ship, Any), OrcShipMove,

        special(PatrolButton, Human, Any, Any), HumanPatrol,
        special(PatrolButton, Orc, Any, Any), OrcPatrol,

        special(StopButton, Human, Any, Basic), HumanStop,
        special(StopButton, Human, Any, Improved), HumanMeleeDefence1,
        special(StopButton, Human, Any, Advanced), HumanMeleeDefence2,
        special(StopButton, Human, Ship, Basic), HumanShipStop,
        special(StopButton, Human, Ship, Improved), HumanArmourPlating1,
        special(StopButton, Human, Ship, Advanced), HumanArmourPlating2,
        special(StopButton, Orc, Any, Basic), OrcStop,
        special(StopButton, Orc, Any, Improved), OrcMeleeDefence1,
        special(StopButton, Orc, Any, Advanced), OrcMeleeDefence2,
        special(StopButton, Orc, Ship, Basic), OrcShipStop,
        special(StopButton, Orc, Ship, Improved), OrcArmourPlating1,
        special(StopButton, Orc, Ship, Advanced), OrcArmourPlating2
    );

    public Identifier getSpecialization(Identifier button, Identifier faction, Identifier attack, Identifier level) {
        Identifier alias = specializations.getOrDefault(button, button);
        return specializations.getOrDefault(special(button, faction, attack, level), alias);
    }

    private static Map<Identifier, Identifier> specializations(Identifier ... types) {
        Map<Identifier, Identifier> result = new HashMap<>();
        for (int index = 0; index < types.length; index += 2) {
            Identifier identifier = types[index];
            Identifier alias = types[index + 1];
            result.put(identifier, alias);

            if (identifier instanceof IconSpecialization) {
                Collection<IconSpecialization> identifiers = specializationsSet((IconSpecialization)identifier);
                identifiers.forEach(specialization -> result.put(specialization, alias));
            }
        }
        return result;
    }

    private static Collection<IconSpecialization> specializationsSet(IconSpecialization specialization) {
        Identifier faction = specialization.getFaction();
        Collection<Identifier> factions = faction == Any ? asList(WarcraftFaction.values()) : singletonList(faction);

        Identifier attack = specialization.getAttack();
        Collection<Identifier> attacks = attack == Any ? asList(UnitAttack.values()) : singletonList(attack);

        Identifier level = specialization.getLevel();
        Collection<Identifier> levels = level == Any ? asList(IconLevel.values()) : singletonList(level);

        Identifier button = specialization.getButton();
        Collection<IconSpecialization> result = new ArrayList<>();

        factions.forEach(fact ->
            attacks.forEach(att ->
                levels.forEach(lev ->
                    result.add(new IconSpecialization(button, fact, att, lev)))));

        return result;
    }
}
