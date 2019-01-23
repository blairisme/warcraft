/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ai;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ActionContext;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.action.BasicActionContext;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.SpatialGraph;
import com.evilbird.engine.item.SpatialItemNode;
import com.evilbird.warcraft.action.identifier.GeneralActions;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.evilbird.engine.item.ItemPredicates.itemWithClass;

/**
 * Instances of this {@link AiProcedure} instruct AI combatants to attack an
 * enemy if they move nearby.
 *
 * @author Blair Butterworth
 */
//TODO: Improve performance - caching? Consider putting caching into ItemGroup
public class InitiateAttack implements AiProcedure
{
    private ActionFactory actionFactory;

    @Inject
    public InitiateAttack(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    @Override
    public void update(ItemRoot gameState) {
        for (Item movedItem: getMovedItems(gameState)) {
            for (Combatant enemyCombatant: getEnemyCombatants(movedItem)) {
                if (shouldAttack(enemyCombatant, movedItem)) {
                    attack(enemyCombatant, movedItem);
                }
            }
        }
    }

    private Collection<Item> getMovedItems(ItemRoot gameState) {
        SpatialGraph spatialGraph = gameState.getSpatialGraph();
        Map<Item, SpatialItemNode> newOccupants = spatialGraph.getNewOccupants();
        return newOccupants.keySet();
    }

    private Collection<Combatant> getEnemyCombatants(Item item) {
        Collection<Combatant> enemyCombatants = new ArrayList<>();
        for (Player enemyPlayer: getEnemyPlayers(item)) {
            enemyCombatants.addAll(getCombatants(enemyPlayer));
        }
        return enemyCombatants;
    }

//    private Collection<Item> getAiPlayers(ItemRoot world) {
//        if (aiPlayers == null) {
//            aiPlayers = world.findAll(itemWithType(DataType.Player));
//        }
//        return aiPlayers;
//    }

    private Collection<Player> getEnemyPlayers(Item item) {
        Player player = (Player)item.getParent();
        ItemRoot root = item.getRoot();

        Collection<Player> players = root.findAll(itemWithClass(Player.class));
        players.remove(player);

        return players;
    }

    private Collection<Combatant> getCombatants(Player player) {
        return player.findAll(itemWithClass(Combatant.class));
    }

    private boolean shouldAttack(Combatant combatant, Item target) {
        return isIdle(combatant) && withinAttackRange(combatant, target);
    }

    private boolean isIdle(Combatant combatant) {
        return !combatant.hasActions();
    }

    private boolean withinAttackRange(Combatant combatant, Item target) {
        Vector2 combatantPosition = combatant.getPosition();
        Vector2 targetPosition = target.getPosition();
        float range = combatant.getSight();
        float distance = combatantPosition.dst(targetPosition);
        return distance <= range;
    }

    private void attack(Combatant combatant, Item target) {
        ActionContext context = new BasicActionContext(combatant, target, null);
        Action action = actionFactory.newAction(GeneralActions.Attack, context);
        combatant.addAction(action);
    }
}
