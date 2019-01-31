/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.hud.control.status.resource;

import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.resource.Resource;
import com.evilbird.warcraft.item.unit.resource.ResourceType;

import javax.inject.Inject;

public class GoldMineDetailsPane extends GridPane
{
    private Resource resource;
    private TextLabel label;

    @Inject
    public GoldMineDetailsPane() {
        super(1, 1);
        label = createLabel("Gold Left");

        setSize(160, 100);
        setCellSpacing(4);
        setCell(label, 0, 0);
        setCellWidthMinimum(160);
        setCellHeightMinimum(12);
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        label.setText(getText("Gold Left", resource.getResource(ResourceType.Gold)));
    }

    private String getText(String prefix, float suffix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append(": ");
        stringBuilder.append(Math.round(suffix));
        return stringBuilder.toString();
    }

    private TextLabel createLabel(String text) {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(160, 12);
        result.setAlignment(TextLabelAlignment.Center);
        return result;
    }
}
