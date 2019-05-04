/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.data.player;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.DataType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Instances of this class represent a player, either real or artificial.
 * Player items own other units as well as storing accumulated resources.
 *
 * @author Blair Butterworth
 */
public class Player extends ItemGroup implements ResourceContainer
{
    private boolean corporeal;
    private String description;
    private Map<String, Double> statistics;
    private Map<String, Double> resources;
    private transient Skin skin;
    private transient Music music;

    @Inject
    public Player(Skin skin) {
        this.skin = skin;
        this.resources = new LinkedHashMap<>();
        this.statistics = new LinkedHashMap<>();

        setType(DataType.Player);
        setPosition(0, 0);
        setSize(Float.MAX_VALUE, Float.MAX_VALUE);

        initializeMusic();
    }

    private void initializeMusic() {
        PlayerStyle playerStyle = skin.get("default", PlayerStyle.class);
        music = playerStyle.music;
        music.setVolume(0.3f);
    }

    public boolean isCorporeal() {
        return corporeal;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public float getResource(ResourceType type) {
        String key = type.name();
        if (resources.containsKey(key)){
            return resources.get(key).floatValue();
        }
        return 0;
    }

    public int getStatistic(PlayerStatistic type) {
        String key = type.name();
        if (statistics.containsKey(key)) {
            return statistics.get(key).intValue();
        }
        return 0;
    }

    public void setCorporeal(boolean corporeal) {
        this.corporeal = corporeal;

        if (corporeal) {
            music.play();
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setResource(ResourceType type, float value) {
        String key = type.name();
        resources.put(key, (double)value);
    }

    public void setStatistic(PlayerStatistic type, float value) {
        String key = type.name();
        statistics.put(key, (double)value);
    }

    public void incrementStatistic(PlayerStatistic type, float value) {
        float current = getStatistic(type);
        float updated = current + value;
        setStatistic(type, updated);
    }

    @Override
    public void setSize(Vector2 size) {
    }

    @Override
    public void setSize(float width, float height) {
    }

    @Override
    public void setPosition(Vector2 position) {
    }

    @Override
    public void setPosition(float x, float y) {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != getClass()) return false;

        Player player = (Player)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(description, player.description)
            .append(corporeal, player.corporeal)
            .append(resources, player.resources)
            .append(statistics, player.statistics)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(description)
            .append(corporeal)
            .append(resources)
            .append(statistics)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper("base")
            .append("description", description)
            .append("corporeal", corporeal)
            .append("resources", resources)
            .toString();
    }
}
