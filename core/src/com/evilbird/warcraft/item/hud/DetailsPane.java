package com.evilbird.warcraft.item.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.engine.item.control.TextLabel;

import javax.inject.Provider;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class DetailsPane extends GridPane
{
    private Provider<UnitPane> unitPaneProvider;

    public DetailsPane(Provider<UnitPane> unitPaneProvider)
    {
        super(1, 2);
        this.unitPaneProvider = unitPaneProvider;
        initialize();
    }

    private void initialize()
    {
        setSize(176, 176);
        setCellPadding(5);
        setCellSpacing(15);
        setCellWidthMinimum(170);
        setCellHeightMinimum(50);
        setId(new Identifier("DetailsPane"));
        setType(new Identifier("DetailsPane"));
        setTouchable(Touchable.disabled);
    }

    public void setItem(Item item)
    {
        clearCells();
        setTitle(item);
        setDetails(item);
    }

    private void setTitle(Item item)
    {
        UnitPane unitPane = unitPaneProvider.get();
        unitPane.setItem(item);
        unitPane.setSize(54, 53);

        TextLabel name = createLabel("Footman", 100, 15);
        TextLabel level = createLabel("Level 1", 100, 15);

        GridPane nameContainer = new GridPane(1, 2);
        nameContainer.setSize(110, 50);
        nameContainer.setCellPadding(4);
        nameContainer.setCellSpacing(4);
        nameContainer.setCell(name, 0, 0);
        nameContainer.setCell(level, 0, 1);

        GridPane titleContainer = new GridPane(2, 1);
        titleContainer.setSize(160, 50);
        titleContainer.setCellSpacing(0);
        titleContainer.setCell(unitPane, 0, 0);
        titleContainer.setCell(nameContainer, 1, 0);

        setCell(titleContainer, 0, 0);
    }

    private void setDetails(Item item)
    {
        /*
        --- Farm ---
        Food Usage:
            Grown: 5
            Used: 4
         */

        /*
        --- Townhall ---
        Production:
            Gold: 100
            Lumber: 100
            Oil: 100
         */

        /*
        --- Footman ---
            Armour: 2
            Damage: 2-9
            Range: 1
            Sight 4
            Speed: 10
        */

        /*
        --- Barracks ---
         */

        /*
        --- Gold Mine ---
            Gold left 123
         */

        TextLabel armour = createLabel("Armour: 2", 160, 12);
        TextLabel damage = createLabel("Damage: 2-9", 160, 12);
        TextLabel range = createLabel("Range: 1", 160, 12);
        TextLabel sight = createLabel("Sight: 4", 160, 12);
        TextLabel speed = createLabel("Speed: 10", 160, 12);

        GridPane detailsContainer = new GridPane(1, 5);
        detailsContainer.setSize(160, 100);
        detailsContainer.setCellSpacing(4);
        detailsContainer.setCell(armour, 0, 0);
        detailsContainer.setCell(damage, 0, 1);
        detailsContainer.setCell(range, 0, 2);
        detailsContainer.setCell(sight, 0, 3);
        detailsContainer.setCell(speed, 0, 4);
        detailsContainer.setCellWidthMinimum(160);
        detailsContainer.setCellHeightMinimum(12);

        setCell(detailsContainer, 0, 1);
    }

    private TextLabel createLabel(String text, float width, float height)
    {
        TextLabel result = new TextLabel();
        result.setText(text);
        result.setSize(width, height);
        return result;
    }
}
