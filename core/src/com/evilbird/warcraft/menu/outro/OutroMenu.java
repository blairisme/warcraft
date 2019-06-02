/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.evilbird.engine.common.control.SelectListener;
import com.evilbird.engine.common.control.SelectListenerAdapter;
import com.evilbird.engine.common.control.TextProgressBar;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatistic;

import javax.inject.Inject;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getAiPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Buildings;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Gold;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Kills;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Oil;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Razed;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Score;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Units;
import static com.evilbird.warcraft.item.data.player.PlayerStatistic.Wood;
import static com.evilbird.warcraft.menu.outro.OutroMenuType.Victory;
import static java.util.Arrays.asList;

/**
 * Represents a menu shown when a scenario is completed or failed. The menu
 * contains a table of statistics, detailing the units created and killed by
 * the player and their enemies as well as the resources they gathered.
 *
 * @author Blair Butterworth
 */
public class OutroMenu extends Menu
{
    private Skin skin;
    private OutroMenuType type;
    private OutroMenuStrings strings;
    private Table container;
    private Table summary;
    private Table details;
    private TextButton button;

    @Inject
    public OutroMenu(DeviceDisplay display, Skin skin) {
        super(display);
        this.skin = skin;
        this.container = createContainer();
        this.summary = createSummary();
        this.details = createDetails();
        this.button = createButton();
    }

    public Skin getSkin() {
        return skin;
    }

    public void setType(OutroMenuType type) {
        this.type = type;
        this.container.setBackground(type == Victory ? "background-victory" : "background-defeat");
    }

    public void setLabelBundle(OutroMenuStrings strings) {
        this.strings = strings;
        this.button.setText(strings.getButtonLabel());
    }

    public void setButtonAction(SelectListener action) {
        this.button.addListener(new SelectListenerAdapter(action));
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);

        GameEngine engine = (GameEngine)controller;
        State state = engine.getState();

        if (state != null) {
            updateView(state);
        }
    }

    private void updateView(State state) {
        ItemRoot world = state.getWorld();
        Player human = getHumanPlayer(world);
        Player ai = getAiPlayer(world);

        updateSummary(human);
        updateDetails(human, ai);
    }

    private Table createContainer() {
        Table table = new Table(skin);
        table.setFillParent(true);

        Stage stage = getStage();
        stage.addActor(table);

        return table;
    }

    private Table createSummary() {
        Table summary = new Table(skin);
        Cell cell = container.add(summary);
        cell.align(Align.top);
        cell.growX();
        cell.height(percentHeight(0.45f, container));

        cell.row();
        return summary;
    }

    private void updateSummary(Player player) {
        summary.clear();
        int score = getScore(player);
        addSummaryLabel(strings.getOutcomeLabel(), strings.getOutcome(type));
        addSummaryLabel(strings.getRankLabel(), strings.getRank(score));
        addSummaryLabel(strings.getScoreLabel(), String.valueOf(score));
    }

    private int getScore(Player player) {
        int score = player.getStatistic(Score);
        return type == Victory ? score + 500 : score;
    }

    private void addSummaryLabel(String title, String value) {
        Table labelSet = new Table();
        Cell cell = summary.add(labelSet);
        cell.growX();

        addSummaryLabel(labelSet, title, "default");
        addSummaryLabel(labelSet, value, "font-large");
    }

    private void addSummaryLabel(Table labelset, String text, String font) {
        Label label = new Label(text, skin, font);
        label.setAlignment(Align.center);

        Cell cell = labelset.add(label);
        cell.expand();
        cell.fill();
        cell.row();
    }

    private Table createDetails() {
        Table details = new Table(skin);
        Cell cell = container.add(details);
        cell.align(Align.top);
        cell.growX();
        cell.height(percentHeight(0.45f, container));
        cell.row();
        return details;
    }

    private void updateDetails(Player human, Player ai) {
        details.clear();
        addDetailsHeaders();
        addDetailValues(human, ai);
    }

    private void addDetailsHeaders() {
        addDetailsHeader(strings.getUnitLabel());
        addDetailsHeader(strings.getBuildingsLabel());
        addDetailsHeader(strings.getGoldLabel());
        addDetailsHeader(strings.getWoodLabel());
        addDetailsHeader(strings.getOilLabel());
        addDetailsHeader(strings.getKillsLabel());
        addDetailsHeader(strings.getRazedLabel());
        details.row();
    }

    private void addDetailsHeader(String text) {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center);

        Cell cell = details.add(label);
        cell.expandX();
        cell.fillX();
        cell.padBottom(12);
    }

    private void addDetailValues(Player human, Player ai) {
        Map<PlayerStatistic, Float> largestValues = getLargestStatistics(asList(human, ai));
        addDetailsValues(human, largestValues);
        addDetailsTextLine(human);
        addDetailsValues(ai, largestValues);
        addDetailsTextLine(ai);
    }

    private EnumSet<PlayerStatistic> getDetailStatistics() {
        return EnumSet.of(Units, Buildings, Gold, Wood, Oil, Kills, Razed);
    }

    private Map<PlayerStatistic, Float> getLargestStatistics(List<Player> players) {
        Map<PlayerStatistic, Float> result = new HashMap<>();
        for (PlayerStatistic statistic: getDetailStatistics()) {
            float maximum = 0;
            for (Player player: players) {
                maximum = Math.max(maximum, player.getStatistic(statistic));
            }
            result.put(statistic, maximum);
        }
        return result;
    }

    private void addDetailsValues(Player player, Map<PlayerStatistic, Float> largestValues) {
        for (PlayerStatistic statistic: getDetailStatistics()) {
            addDetailsValue(player.getStatistic(statistic), largestValues.get(statistic));
        }
        details.row();
    }

    private void addDetailsValue(float value, float max) {
        TextProgressBar bar = new TextProgressBar(0, max, 1, "", skin, "progress-outro");
        bar.setValue(value);
        bar.setText(String.valueOf((int)value));

        Cell cell = details.add(bar);
        cell.align(Align.center);
        cell.width(94);
        cell.height(28);
        cell.padLeft(8);
        cell.padRight(8);
    }

    private void addDetailsTextLine(Player player){
        String playerName = strings.getPlayerName(player);
        Label textLine = new Label(playerName, skin, "font-large");
        textLine.setAlignment(Align.center);

        Cell cell = details.add(textLine);
        cell.colspan(7);
        cell.expandX();
        cell.fillX();
        cell.padTop(10);
        cell.padBottom(60);

        cell.row();
    }

    private TextButton createButton() {
        TextButton result = new TextButton("", skin);

        Cell cell = container.add(result);
        cell.align(Align.right);
        cell.pad(20);
        container.row();

        return result;
    }
}
