package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.hud.control.Table;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ControlPanel extends Item
{
    private Table container;

    @Inject
    public ControlPanel(
        ActionPanelProvider actionPanelProvider,
        MinimapPanelProvider minimapPanelProvider,
        SelectionPanelProvider selectionPanelProvider)
    {
        MinimapPanel minimapPanel = minimapPanelProvider.get();
        minimapPanel.setSize(176, 176);

        SelectionPanel selectionPanel = selectionPanelProvider.get();
        selectionPanel.setSize(176, 176);

        ActionPanel actionPanel = actionPanelProvider.get();
        actionPanel.setSize(176, 136);

        this.container = new Table(1, 3);
        this.container.setSize(176, 488);
        this.container.setPosition(0, 140); //TODO - Replace with Align left center
        this.container.setCellPadding(0);
        this.container.setCellSpacing(0);
        this.container.setCell(minimapPanel, 0, 0);
        this.container.setCell(selectionPanel, 0, 1);
        this.container.setCell(actionPanel, 0, 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        container.draw(batch, parentAlpha);
    }

    @Override //TODO: Investigate better implementation
    protected void positionChanged()
    {
        container.setPosition(getX(), getY());
    }

    @Override //TODO: Investigate better implementation
    protected void sizeChanged()
    {
        container.setSize(getWidth(), getHeight());
    }
}
