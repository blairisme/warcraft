/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.status.details.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.common.resource.ResourceContainer;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.display.control.status.details.DetailsPaneElement;
import com.evilbird.warcraft.item.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.resource.Resource;

/**
 * Instances of this user interface show details about selectable
 * {@link Resource Resources}.
 *
 * @author Blair Butterworth
 */
public class ResourceDetailsPane extends Grid implements DetailsPaneElement
{
    private ResourceContainer resource;
    private Label label;
    private Skin skin;
    private com.evilbird.warcraft.item.display.control.status.details.DetailsPaneStyle style;

    public ResourceDetailsPane(Skin skin) {
        super(1, 1);
        setSkin(skin);
        setSize(160, 100);
        setCellSpacing(4);
        setCellWidth(160);
        setCellHeight(12);
    }

    public void setItem(Item item) {
        resource = (ResourceContainer)item;
        createView(resource);
    }

    public void setResource(Item item, ResourceType type, float value) {
        if (resource == item && type == getType(resource)) {
            label.setText(getText(resource, value));
        }
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        this.skin = skin;
        this.style = skin.get(DetailsPaneStyle.class);
    }

    private void createView(ResourceContainer resource) {
        clearItems();
        add(addLabel(getText(resource), skin));
    }

    private String getText(ResourceContainer container) {
        return getText(container, getValue(container));
    }

    private String getText(ResourceContainer resource, float value) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return style.strings.getGoldRemaining(value);
            case OilPatch:
            case OilPlatform:
            case OilRig: return style.strings.getOilRemaining(value);
            default: throw new UnsupportedOperationException();
        }
    }

    private ResourceType getType(ResourceContainer resource) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return ResourceType.Gold;
            case OilPatch:
            case OilPlatform:
            case OilRig: return ResourceType.Oil;
            default: throw new UnsupportedOperationException();
        }
    }

    private float getValue(ResourceContainer resource) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return resource.getResource(ResourceType.Gold);
            case OilPatch:
            case OilPlatform:
            case OilRig: return resource.getResource(ResourceType.Oil);
            default: throw new UnsupportedOperationException();
        }
    }

    private Label addLabel(String text, Skin skin) {
        Label result = new Label(text, skin);
        result.setSize(160, 12);
        result.setAlignment(Align.center);
        add(result);
        return result;
    }
}
