/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.TextProgressBar;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.ui.display.control.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;

/**
 * A user interface control that displays spell caster attributes.
 *
 * @author Blair Butterworth
 */
public class SpellCasterDetailsPane extends CombatantDetailsPane
{
    private Label header;
    private TextProgressBar manaBar;
    private SpellCaster spellCaster;

    public SpellCasterDetailsPane(Skin skin) {
        super(skin);
        Grid container = addContainer();
        header = addLabel(container, skin);
        manaBar = addBar(container, skin);
    }

    @Override
    public void setItem(Item item) {
        super.setItem(item);
        spellCaster = (SpellCaster)item;
        updateManaBar(spellCaster.getMana());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        updateManaBar(spellCaster.getMana());
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        if (header != null) {
            DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);
            header.setText(style.strings.getMagic());
        }
    }

    private Grid addContainer() {
        Grid container = new Grid(2, 1);
        add(container);
        return container;
    }

    private Label addLabel(Grid container, Skin skin) {
        DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);

        Label label = new Label(style.strings.getMagic(), skin);
        label.setAlignment(Align.right);

        Cell labelCell = container.add(label);
        labelCell.growX();

        return label;
    }

    private TextProgressBar addBar(Grid container, Skin skin) {
        TextProgressBar bar = new TextProgressBar(0, 200, 1, "", skin, "mana-bar");
        bar.setSize(76, 17);

        Cell cell = container.add(bar);
        cell.width(76);
        cell.height(17);

        return bar;
    }

    private void updateManaBar(float mana) {
        manaBar.setValue(mana);
        manaBar.setText(Integer.toString(Math.round(mana)));
    }
}
