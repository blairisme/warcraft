/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.order;

import com.evilbird.warcraft.behaviour.WarcraftBehaviourType;
import com.evilbird.warcraft.behaviour.ai.order.campaign.HumanCampaign3;
import com.evilbird.warcraft.behaviour.ai.order.campaign.HumanCampaign4;
import com.evilbird.warcraft.behaviour.ai.order.campaign.OrcCampaign3;
import com.evilbird.warcraft.behaviour.ai.order.campaign.OrcCampaign4;
import com.evilbird.warcraft.behaviour.ai.order.common.EmptyOrder;
import com.evilbird.warcraft.behaviour.ai.order.scenario.HumanEasy;
import com.evilbird.warcraft.behaviour.ai.order.scenario.OrcEasy;
import com.evilbird.warcraft.common.WarcraftFaction;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Instances of this class provide {@link OperationOrder} implementations
 * appropriate for given player and behaviour types.
 *
 * @author Blair Butterworth
 */
public class OperationOrderFactory
{
    @Inject
    public OperationOrderFactory() {
    }

    public OperationOrder corporealBehaviour() {
        return emptyBehaviour();
    }

    public OperationOrder neutralBehaviour() {
        return emptyBehaviour();
    }

    public OperationOrder enemyBehaviour(WarcraftFaction faction, WarcraftBehaviourType behaviour) {
        if (behaviour.isHumanCampaign()) {
            return humanCampaignBehaviour(behaviour);
        }
        if (behaviour.isOrcCampaign()) {
            return orcCampaignBehaviour(behaviour);
        }
        if (behaviour.isScenario()) {
            if (faction == Human) {
                return humanScenarioBehaviour(behaviour);
            }
            if (faction == Orc) {
                return orcScenarioBehaviour(behaviour);
            }
        }
        return emptyBehaviour();
    }

    private OperationOrder humanCampaignBehaviour(WarcraftBehaviourType type) {
        switch (type) {
            case Human3: return new HumanCampaign3();
            case Human4: return new HumanCampaign4();
            default: return emptyBehaviour();
        }
    }

    private OperationOrder orcCampaignBehaviour(WarcraftBehaviourType type) {
        switch (type) {
            case Orc3: return new OrcCampaign3();
            case Orc4: return new OrcCampaign4();
            default: return emptyBehaviour();
        }
    }

    private OperationOrder humanScenarioBehaviour(WarcraftBehaviourType type) {
        return new HumanEasy();
    }

    private OperationOrder orcScenarioBehaviour(WarcraftBehaviourType type) {
        return new OrcEasy();
    }

    private OperationOrder emptyBehaviour() {
        return new EmptyOrder();
    }
}
