package com.evilbird.warcraft.item.hud.state;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.ProgressBar;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;
import com.evilbird.warcraft.item.unit.building.Building;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Localize text
public class BuildingBar extends GridPane
{
    private Building building;
    private ProgressBar progressBar;

    public BuildingBar()
    {
        super(1, 1);

        progressBar = new ProgressBar();
        progressBar.setSize(152, 14);
        progressBar.setProgress(0f);

        TextLabel label = new TextLabel();
        label.setText("% Complete");
        label.setSize(152, 14);
        label.setAlignment(TextLabelAlignment.Center);

        ItemGroup stack = new ItemGroup();
        stack.addItem(progressBar);
        stack.addItem(label);
        stack.setSize(152, 14);

        setSize(158, 20);
        setCellPadding(3);
        setCell(stack, 0, 0);

        setType(new NamedIdentifier("BuildingProgressBar"));
    }

    public void setBuilding(Building building)
    {
        this.building = building;
    }

    public void setProgressTexture(Drawable texture)
    {
        progressBar.setTexture(texture);
    }

    @Override
    public void update(float delta)
    {
        progressBar.setProgress(building.getProgress());
        super.update(delta);
    }
}
