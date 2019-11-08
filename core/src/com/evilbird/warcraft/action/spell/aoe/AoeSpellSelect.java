/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.aoe;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.target.TargetSelectorType;
import com.evilbird.warcraft.item.unit.Unit;

import javax.inject.Inject;

import static com.evilbird.engine.action.ActionConstants.ActionComplete;
import static com.evilbird.engine.item.utility.ItemOperations.getScreenCenter;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_HEIGHT;
import static com.evilbird.warcraft.item.WarcraftItemConstants.TILE_WIDTH;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getPlayer;

/**
 * @author Blair Butterworth
 */
public class AoeSpellSelect extends BasicAction
{
    private ItemFactory factory;
    private TargetSelectorType type;

    @Inject
    public AoeSpellSelect(ItemFactory factory) {
        this.factory = factory;
        this.type = TargetSelectorType.BlizzardSelector;
    }

    @Override
    public boolean act(float time) {
        Unit caster = (Unit)getItem();
        Player player = getPlayer(caster);

        Item selector = factory.get(type);
        selector.setPosition(getPosition(caster));
        player.addItem(selector);

        caster.setAssociatedItem(selector);
        return ActionComplete;
    }

    private Vector2 getPosition(Item caster) {
        Vector2 screenCenter = getScreenCenter(caster);
        screenCenter.x = Math.round(screenCenter.x/TILE_WIDTH) * TILE_WIDTH;
        screenCenter.y = Math.round(screenCenter.y/TILE_HEIGHT) * TILE_HEIGHT;
        return screenCenter;
    }
}
