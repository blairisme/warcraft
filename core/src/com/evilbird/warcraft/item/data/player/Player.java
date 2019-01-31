/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Instances of this class represent a player, either real or artificial.
 * Player items own other units as well as storing accumulated resources.
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup implements ResourceContainer
{
    private boolean humanPlayer;
    private Map<ResourceIdentifier, Float> resources;

    @Inject
    public Player() {
        super.setPosition(0, 0);
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
        super.setType(DataType.Player);
        resources = new HashMap<>();
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    @Override
    public float getResource(ResourceIdentifier resource) {
        Float result = resources.get(resource);
        return result != null ? result : 0f;
    }

    @Override
    public Map<ResourceIdentifier, Float> getResources() {
        return resources;
    }

    public void setHumanPlayer(boolean humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    @Override
    public void setResource(ResourceIdentifier type, float value) {
        this.resources.put(type, value);
    }

    @Override
    public void setResources(Map<ResourceIdentifier, Float> resources) {
        this.resources = resources;
    }

    @Override
    public void setSize(Vector2 size) {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    @Override
    public void setPosition(Vector2 position) {
        super.setPosition(0, 0);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(0, 0);
    }
}
