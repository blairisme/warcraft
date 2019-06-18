/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common.upgrade;

import com.evilbird.engine.events.Event;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;

/**
 * Instances of this {@link Event} are produced when an upgrade is applied to a
 * player.
 *
 * @author Blair Butterworth
 */
public class UpgradeEvent implements Event
{
    private Player player;
    private PlayerUpgrade upgrade;
    private int value;

    public UpgradeEvent(Player player, PlayerUpgrade upgrade, int value) {
        this.player = player;
        this.upgrade = upgrade;
        this.value = value;
    }

    @Override
    public Item getSubject() {
        return player;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerUpgrade getUpgrade() {
        return upgrade;
    }

    public int getValue() {
        return value;
    }
}
