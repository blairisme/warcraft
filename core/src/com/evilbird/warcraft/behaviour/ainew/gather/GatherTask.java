/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ainew.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.gather.GatherActions;
import com.evilbird.warcraft.behaviour.ainew.common.framework.AsyncActionTask;
import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.object.layer.LayerCell;
import com.evilbird.warcraft.object.layer.forest.ForestCell;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.resource.Resource;

import javax.inject.Inject;

/**
 * An action task that assigns a gathering action to the subject contained in
 * the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class GatherTask extends AsyncActionTask<GatherData>
{
    @Inject
    public GatherTask(ActionFactory actionFactory) {
        super(actionFactory);
    }
    
    @Override
    protected Action getAction(ActionFactory factory) {
        GatherData data = getObject();
        ResourceContainer resource = data.getResource();
        GatherActions identifier = getIdentifier(resource);
        Action action = factory.get(identifier);

        GameObject gatherer = data.getGatherer();
        action.setSubject(gatherer);
        action.setTarget(resource);

        gatherer.removeActions();
        gatherer.addAction(action);

        return action;
    }

    private GatherActions getIdentifier(ResourceContainer container) {
        if (container instanceof Resource) {
            return getResourceIdentifier((Resource)container);
        }
        if (container instanceof LayerCell) {
            return getLayerIdentifier((LayerCell)container);
        }
        throw new UnsupportedOperationException();
    }

    private GatherActions getResourceIdentifier(Resource resource) {
        UnitType type = (UnitType)resource.getType();

        if (type == UnitType.GoldMine) {
            return GatherActions.GatherGold;
        }
        if (type == UnitType.OilPlatform || type == UnitType.OilRig) {
            return GatherActions.GatherOil;
        }
        throw new UnsupportedOperationException();
    }

    private GatherActions getLayerIdentifier(LayerCell layerCell) {
        if (layerCell instanceof ForestCell) {
            return GatherActions.GatherWood;
        }
        throw new UnsupportedOperationException();
    }
}
