/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.upgrade;

import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.events.Events;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;

/**
 * An action that applies a given upgrade to the Player that owns the subject
 * of the action.
 *
 * @author Blair Butterworth
 */
public class UpgradeAction extends BasicAction
{
    private Events events;
    private PlayerUpgrade upgrade;

    public UpgradeAction(PlayerUpgrade upgrade, Events events) {
        this.upgrade = upgrade;
        this.events = events;
    }

    public static UpgradeAction applyUpgrade(PlayerUpgrade upgrade, Events events) {
        return new UpgradeAction(upgrade, events);
    }

    @Override
    public boolean act(float delta) {
        Item item = getItem();
        Player player = UnitOperations.getPlayer(item);
        player.setUpgrade(upgrade, true);
        events.add(new UpgradeEvent(player, upgrade, true));
        return true;
    }
}
