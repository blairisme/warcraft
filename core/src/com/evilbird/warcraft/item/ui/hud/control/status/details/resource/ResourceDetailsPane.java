/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.status.details.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.GridItem;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.ui.hud.control.status.details.DetailsPaneElement;
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
    private Resource resource;
    private Label resourceLabel;
    private float resourceValue;

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
        this.resource = (Resource)item;
        this.resourceValue = -1;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (resource != null) {
            float newResourceValue = getResourceValue(resource);
            if (resourceValue != newResourceValue) {
                resourceValue = newResourceValue;

                String name = getResourceName(resource);
                String amount = String.valueOf(Math.round(resourceValue));
                resourceLabel.setText(name + amount);
            }
        }
    }

    private String getResourceName(Resource resource) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return "Gold Left: ";
            case OilPatch: return "Oil Left: ";
            default: throw new UnsupportedOperationException();
        }
    }

    private float getResourceValue(Resource resource) {
        switch ((UnitType)resource.getType()) {
            case GoldMine: return resource.getResource(ResourceType.Gold);
            case OilPatch: return resource.getResource(ResourceType.Oil);
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
