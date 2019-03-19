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
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.*;

/**
 * Instances of this class represent a player, either real or artificial.
 * Player items own other units as well as storing accumulated resources.
 *
 * @author Blair Butterworth
 */
//TODO: Resource handling is pants. Improve!
public class Player extends ItemGroup implements ResourceContainer
{
    private boolean humanPlayer;
    private HashSet<Resource> resources;
    private transient Map<ResourceIdentifier, Resource> index;

    @Inject
    public Player() {
        super.setPosition(0, 0);
        super.setSize(Float.MAX_VALUE, Float.MAX_VALUE);
        super.setType(DataType.Player);
        resources = new HashSet<>();
    }

    public boolean isHumanPlayer() {
        return humanPlayer;
    }

    @Override
    public float getResource(ResourceIdentifier id) {
        Resource resource = getResourceContainer(id);
        return resource.getValue();
    }

    public void setHumanPlayer(boolean humanPlayer) {
        this.humanPlayer = humanPlayer;
    }

    @Override
    public void setResource(ResourceIdentifier id, float value) {
        Resource resource = getResourceContainer(id);
        resource.setValue(value);
        setResourceContainer(resource);
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Player player = (Player)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(humanPlayer, player.humanPlayer)
            .append(resources, player.resources)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(humanPlayer)
            .append(resources)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("humanPlayer", humanPlayer)
            .append("resources", resources)
            .toString();
    }

    private Resource getResourceContainer(ResourceIdentifier id) {
        initializeIndex();
        if (index.containsKey(id)) {
            return index.get(id);
        }
        return new Resource(id, 0);
    }

    private void setResourceContainer(Resource resource) {
        initializeIndex();

        Resource current = index.get(resource.getId());
        resources.remove(current);

        resources.add(resource);
        index.put(resource.getId(), resource);
    }

    private void initializeIndex() {
        if (index == null) {
            index = new HashMap<>();
            resources.forEach(resource -> index.put(resource.getId(), resource));
        }
    }
}
