/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.hud.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.test.data.device.TestTextures;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.item.data.player.PlayerUpgrade;
import com.evilbird.warcraft.item.ui.hud.control.ControlPaneFactory;
import com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitFaction;
import com.evilbird.warcraft.item.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.AttackButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.CancelButton;
import static com.evilbird.warcraft.item.ui.hud.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.ui.hud.control.common.IconLevel.Basic;
import static com.evilbird.warcraft.item.unit.UnitAttack.Melee;
import static com.evilbird.warcraft.item.unit.UnitFaction.Human;
import static org.junit.Assert.*;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class IconSetTest extends GameTestCase
{
    private IconSet iconSet;

    @Before
    public void setup() {
        Texture texture = TestTextures.newTextureMock();
        iconSet = new IconSet(texture);
    }

    @Test
    public void getActionButtonCustomTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        Drawable actual = iconSet.get(ActionButtonType.AttackButton, unit);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getActionButtonIconTest() {
        Drawable actual = iconSet.get(ActionButtonType.CancelButton);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUnitTypeIconTest() {
        Drawable actual = iconSet.get(UnitType.Footman);
        Assert.assertNotNull(actual);
    }
}