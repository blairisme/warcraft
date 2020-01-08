/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.status.details.combatant;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.TextProgressBar;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.control.Grid;
import com.evilbird.warcraft.object.display.components.status.details.DetailsPaneStyle;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;

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
    public void setItem(GameObject gameObject) {
        super.setItem(gameObject);
        spellCaster = (SpellCaster) gameObject;
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
