/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatistic;
import com.evilbird.warcraft.item.data.player.PlayerStyle;
import com.evilbird.warcraft.item.data.player.PlayerType;
import com.evilbird.warcraft.item.unit.UnitType;
import org.mockito.Mockito;

import static com.evilbird.test.data.item.TestBuildings.newTestBuilding;
import static com.evilbird.test.data.item.TestCombatants.newTestCombatant;
import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;

public class TestPlayers
{
    private TestPlayers() {
    }

    public static Player newTestPlayer(String id) {
        return newTestPlayer(new TextIdentifier(id));
    }

    public static Player newTestPlayer(Identifier identifier) {
        return newTestPlayer(identifier, newTestRoot("root"));
    }

    public static Player newTestPlayer(Identifier identifier, ItemRoot root) {
        Player player = new Player(getSkin());
        player.setIdentifier(identifier);
        player.setType(PlayerType.Corporeal);
        player.setResource(ResourceType.Gold, 123);
        player.setResource(ResourceType.Wood, 456);
        player.setStatistic(PlayerStatistic.Kills, 4);
        player.setStatistic(PlayerStatistic.Buildings, 10);
        player.addItem(newTestCombatant(new TextIdentifier("footman"), UnitType.Footman, root, player));
        player.addItem(newTestBuilding(new TextIdentifier("barracks"), UnitType.Barracks, root, player));
        player.setRoot(root);
        player.setParent(root.getBaseGroup());
        return player;
    }

    private static Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", getPlayerStyle());
        return skin;
    }

    private static PlayerStyle getPlayerStyle() {
        PlayerStyle playerStyle = new PlayerStyle();
        playerStyle.music = Mockito.mock(Music.class);
        return playerStyle;
    }
}
