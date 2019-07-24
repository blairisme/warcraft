/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.common;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
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
import static com.evilbird.warcraft.item.ui.display.control.common.IconLevel.Advanced;
import static com.evilbird.warcraft.item.ui.display.control.common.IconLevel.Basic;
import static com.evilbird.warcraft.item.ui.display.control.common.IconLevel.Improved;
import static com.evilbird.warcraft.item.ui.display.control.common.IconSpecialization.special;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.Any;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanArmourPlating1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanArmourPlating2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDefend;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDetonate;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanDisembark;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDefence1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMeleeDefence2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanPatrol;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanRangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipGather;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanShipStop;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanSiegeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanSiegeDamage;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.HumanStop;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcArmourPlating1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcArmourPlating2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDefend;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDetonate;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcDisembark;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDefence1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMeleeDefence2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcPatrol;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcRangedDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDamage1;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDamage2;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipDeposit;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipGather;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipMove;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcShipStop;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcSiegeAttack;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcSiegeDamage;
import static com.evilbird.warcraft.item.ui.display.control.common.IconType.OrcStop;
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
        Boar, ActionButtonType.PolymorphButton,
        Seal, ActionButtonType.PolymorphButton,
        Sheep, ActionButtonType.PolymorphButton,

        ActionButtonType.BuildCancelButton, ActionButtonType.CancelButton,

        ActionButtonType.ImprovedMeleeUpgradeButton, MeleeDamage1,
        ActionButtonType.AdvancedMeleeUpgradeButton, MeleeDamage2,
        ActionButtonType.ImprovedRangedUpgradeButton, RangedDamage1,
        ActionButtonType.AdvancedRangedUpgradeButton, RangedDamage2,

        ActionButtonType.BuildBarracksButton, Barracks,
        ActionButtonType.BuildFarmButton, Farm,
        ActionButtonType.BuildLumberMillButton, LumberMill,
        ActionButtonType.BuildOilPlatformButton, OilPlatform,
        ActionButtonType.BuildShipyardButton, Shipyard,
        ActionButtonType.BuildTownHallButton, TownHall,

        ActionButtonType.BuildDockyardButton, Dockyard,
        ActionButtonType.BuildEncampmentButton, Encampment,
        ActionButtonType.BuildGreatHallButton, GreatHall,
        ActionButtonType.BuildOilRigButton, OilRig,
        ActionButtonType.BuildPigFarmButton, PigFarm,
        ActionButtonType.BuildTrollLumberMillButton, TrollLumberMill,

        ActionButtonType.TrainElvenArcherButton, ElvenArcher,
        ActionButtonType.TrainElvenDestroyerButton, ElvenDestroyer,
        ActionButtonType.TrainFootmanButton, Footman,
        ActionButtonType.TrainOilTankerButton, OilTanker,
        ActionButtonType.TrainPeasantButton, Peasant,

        ActionButtonType.TrainGruntButton, Grunt,
        ActionButtonType.TrainPeonButton, Peon,
        ActionButtonType.TrainTrollAxethrowerButton, TrollAxethrower,
        ActionButtonType.TrainTrollDestroyerButton, TrollDestroyer,
        ActionButtonType.TrainTrollTankerButton, TrollTanker,

        special(ActionButtonType.AttackButton, Human, Melee, Basic), HumanMeleeAttack,
        special(ActionButtonType.AttackButton, Human, Melee, Improved), HumanMeleeDamage1,
        special(ActionButtonType.AttackButton, Human, Melee, Advanced), HumanMeleeDamage2,
        special(ActionButtonType.AttackButton, Human, Ranged, Basic), HumanRangedAttack,
        special(ActionButtonType.AttackButton, Human, Ranged, Improved), HumanRangedDamage1,
        special(ActionButtonType.AttackButton, Human, Ranged, Advanced), HumanRangedDamage2,
        special(ActionButtonType.AttackButton, Human, Ship, Basic), HumanShipAttack,
        special(ActionButtonType.AttackButton, Human, Ship, Improved), HumanShipDamage1,
        special(ActionButtonType.AttackButton, Human, Ship, Advanced), HumanShipDamage2,
        special(ActionButtonType.AttackButton, Human, Siege, Basic), HumanSiegeAttack,
        special(ActionButtonType.AttackButton, Human, Siege, Improved), HumanSiegeDamage,
        special(ActionButtonType.AttackButton, Orc, Melee, Basic), OrcMeleeAttack,
        special(ActionButtonType.AttackButton, Orc, Melee, Improved), OrcMeleeDamage1,
        special(ActionButtonType.AttackButton, Orc, Melee, Advanced), OrcMeleeDamage2,
        special(ActionButtonType.AttackButton, Orc, Ranged, Basic), OrcRangedAttack,
        special(ActionButtonType.AttackButton, Orc, Ranged, Improved), OrcRangedDamage1,
        special(ActionButtonType.AttackButton, Orc, Ranged, Advanced), OrcRangedDamage2,
        special(ActionButtonType.AttackButton, Orc, Ship, Basic), OrcShipAttack,
        special(ActionButtonType.AttackButton, Orc, Ship, Improved), OrcShipDamage1,
        special(ActionButtonType.AttackButton, Orc, Ship, Advanced), OrcShipDamage2,
        special(ActionButtonType.AttackButton, Orc, Siege, Basic), OrcSiegeAttack,
        special(ActionButtonType.AttackButton, Orc, Siege, Improved), OrcSiegeDamage,

        special(ActionButtonType.DepositButton, Human, Any, Any), HumanDeposit,
        special(ActionButtonType.DepositButton, Human, Ship, Any), HumanShipDeposit,
        special(ActionButtonType.DepositButton, Orc, Any, Any), OrcDeposit,
        special(ActionButtonType.DepositButton, Orc, Ship, Any), OrcShipDeposit,

        special(ActionButtonType.DefendButton, Human, Any, Any), HumanDefend,
        special(ActionButtonType.DefendButton, Orc, Any, Any), OrcDefend,

        special(ActionButtonType.DetonateButton, Human, Any, Any), HumanDetonate,
        special(ActionButtonType.DetonateButton, Orc, Any, Any), OrcDetonate,

        special(ActionButtonType.DisembarkButton, Human, Any, Any), HumanDisembark,
        special(ActionButtonType.DisembarkButton, Orc, Any, Any), OrcDisembark,

        special(ActionButtonType.GatherButton, Human, Ship, Any), HumanShipGather,
        special(ActionButtonType.GatherButton, Orc, Ship, Any), OrcShipGather,

        special(ActionButtonType.MoveButton, Human, Any, Any), HumanMove,
        special(ActionButtonType.MoveButton, Human, Ship, Any), HumanShipMove,
        special(ActionButtonType.MoveButton, Orc, Any, Any), OrcMove,
        special(ActionButtonType.MoveButton, Orc, Ship, Any), OrcShipMove,

        special(ActionButtonType.PatrolButton, Human, Any, Any), HumanPatrol,
        special(ActionButtonType.PatrolButton, Orc, Any, Any), OrcPatrol,

        special(ActionButtonType.StopButton, Human, Any, Basic), HumanStop,
        special(ActionButtonType.StopButton, Human, Any, Improved), HumanMeleeDefence1,
        special(ActionButtonType.StopButton, Human, Any, Advanced), HumanMeleeDefence2,
        special(ActionButtonType.StopButton, Human, Ship, Basic), HumanShipStop,
        special(ActionButtonType.StopButton, Human, Ship, Improved), HumanArmourPlating1,
        special(ActionButtonType.StopButton, Human, Ship, Advanced), HumanArmourPlating2,
        special(ActionButtonType.StopButton, Orc, Any, Basic), OrcStop,
        special(ActionButtonType.StopButton, Orc, Any, Improved), OrcMeleeDefence1,
        special(ActionButtonType.StopButton, Orc, Any, Advanced), OrcMeleeDefence2,
        special(ActionButtonType.StopButton, Orc, Ship, Basic), OrcShipStop,
        special(ActionButtonType.StopButton, Orc, Ship, Improved), OrcArmourPlating1,
        special(ActionButtonType.StopButton, Orc, Ship, Advanced), OrcArmourPlating2
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
