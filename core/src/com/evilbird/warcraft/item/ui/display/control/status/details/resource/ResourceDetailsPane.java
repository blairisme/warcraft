/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;

/**
 * Instances of this user interface show details about selectable
 * {@link Resource Resources}.
 *
 * @author Blair Butterworth
 */
public class ResourceDetailsPane extends GridItem implements DetailsPaneElement
{
    private ResourceContainer resource;
    private Label resourceLabel;
    private float resourceValue;
    private DetailsPaneStyle style;

    public ResourceDetailsPane(Skin skin) {
        super(1, 1);

        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);

        resourceLabel = createLabel(skin);
        add(resourceLabel);
    }

    public void setItem(Item item) {
        this.resource = (ResourceContainer)item;
        this.resourceValue = -1;
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.style = skin.get(DetailsPaneStyle.class);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (resource != null) {
            float newResourceValue = getResourceValue(resource);
            if (resourceValue != newResourceValue) {
                resourceValue = newResourceValue;
                resourceLabel.setText(getResourceLabel(resource, resourceValue));
            }
        }
    }

    private String getResourceLabel(ResourceContainer resource, float value) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return style.strings.getGoldRemaining(value);
            case OilPatch:
            case OilPlatform:
            case OilRig: return style.strings.getOilRemaining(value);
            default: throw new UnsupportedOperationException();
        }
    }

    private float getResourceValue(ResourceContainer resource) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return resource.getResource(ResourceType.Gold);
            case OilPatch:
            case OilPlatform:
            case OilRig: return resource.getResource(ResourceType.Oil);
            default: throw new UnsupportedOperationException();
        }
    }

    private Label createLabel(Skin skin) {
        Label result = new Label("", skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        return result;
    }
}
