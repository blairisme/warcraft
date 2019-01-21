package com.evilbird.warcraft.item.hud.control.state.building;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.ItemGroup;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.ProgressBar;
import com.evilbird.engine.item.control.TextLabel;
import com.evilbird.engine.item.control.TextLabelAlignment;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
//TODO: Localize text
public class BuildingProgress extends GridPane
{
    private ProgressBar progressBar;

    public BuildingProgress() {
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

    public void setProgress(float progress) {
        progressBar.setProgress(progress);
    }

    public void setProgressTexture(Drawable texture) {
        progressBar.setTexture(texture);
    }
}
