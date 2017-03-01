package com.evilbird.warcraft.item.hud;

import com.evilbird.engine.item.control.GridPanel;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ControlPanel extends GridPanel
{
    @Inject
    public ControlPanel(
        ActionPanelProvider actionPanelProvider,
        MinimapPanelProvider minimapPanelProvider,
        SelectionPanelProvider selectionPanelProvider)
    {
        super(1, 3);
        setSize(176, 488);
        setPosition(0, 140); //TODO - Replace with Align left center
        setCellPadding(0);
        setCellSpacing(0);
        setCell(minimapPanelProvider.get(), 0, 0);
        setCell(selectionPanelProvider.get(), 0, 1);
        setCell(actionPanelProvider.get(), 0, 2);
    }
}
