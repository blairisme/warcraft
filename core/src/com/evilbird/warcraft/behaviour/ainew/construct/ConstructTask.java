/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.construct.ConstructActions;
import com.evilbird.warcraft.behaviour.ainew.common.ActionTask;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;

import javax.inject.Inject;

/**
 * A task that assigns a construction action to the
 *
 * @author Blair Butterworth
 */
public class ConstructTask extends ActionTask<ConstructData>
{
    private GameObjectFactory objectFactory;

    @Inject
    public ConstructTask(ActionFactory actionFactory, GameObjectFactory objectFactory) {
        super(actionFactory);
        this.objectFactory = objectFactory;
    }

    @Override
    protected Action getAction(ActionFactory actionFactory) {
        ConstructData data = getObject();

        Unit builder = data.getBuilder();
        UnitType building = data.getBuilding();

        GameObject selector = objectFactory.get(SelectorType.forBuilding(building));
        selector.setPosition(data.getLocation());

        Player player = data.getPlayer();
        player.addObject(selector);

        Action construct = actionFactory.get(ConstructActions.forProduct(building));
        construct.setSubject(builder);
        construct.setTarget(selector);

        builder.removeActions();
        builder.addAction(construct);

        return construct;
    }
}
