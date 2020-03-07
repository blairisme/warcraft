/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.invade.sea;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.behaviour.ai.operation.invade.InvasionData;

/**
 * A bundle of attributes encapsulating the data required by invasion
 * behaviour involving land units being transported over sea.
 *
 * @author Blair Butterworth
 */
public class SeaInvasionData extends InvasionData
{
    private GameObject transport;
    private GameObject embarkationPoint;
    private GameObject disembarkationPoint;

    public SeaInvasionData(InvasionData invasionData) {
        super(invasionData);
    }

    public GameObject getEmbarkationPoint() {
        return embarkationPoint;
    }

    public GameObject getDisembarkationPoint() {
        return disembarkationPoint;
    }

    public GameObject getTransport() {
        return transport;
    }

    public void setEmbarkationPoint(GameObject embarkationPoint) {
        this.embarkationPoint = embarkationPoint;
    }

    public void setDisembarkationPoint(GameObject disembarkationPoint) {
        this.disembarkationPoint = disembarkationPoint;
    }

    public void setTransport(GameObject transport) {
        this.transport = transport;
    }
}
