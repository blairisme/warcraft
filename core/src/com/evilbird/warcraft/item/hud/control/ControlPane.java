package com.evilbird.warcraft.item.hud.control;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.item.hud.action.ActionPaneProvider;
import com.evilbird.warcraft.item.hud.minimap.MinimapPaneProvider;
import com.evilbird.warcraft.item.hud.state.StatePane;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ControlPane extends GridPane
{
    @Inject
    public ControlPane(
        ActionPaneProvider actionPaneProvider,
        MinimapPaneProvider minimapPaneProvider,
        Provider<StatePane> statePaneProvider)
    {
        super(1, 3);
        setSize(176, 488);
        setPosition(0, 140); //TODO - Replace with Align left center
        setCellPadding(0);
        setCellSpacing(0);
        setCell(minimapPaneProvider.get(), 0, 0);
        setCell(statePaneProvider.get(), 0, 1);
        setCell(actionPaneProvider.get(), 0, 2);
        setType(new NamedIdentifier("ControlPane"));
        setTouchable(Touchable.enabled);
    }
}
