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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.control.SelectListener;
import com.evilbird.engine.control.SelectListenerAdapter;
import com.evilbird.engine.control.TextProgressBar;
import com.evilbird.engine.game.GameController;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.state.State;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.data.player.PlayerStatisticType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.scenes.scene2d.ui.Value.percentHeight;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getAiPlayer;
import static com.evilbird.warcraft.item.common.query.UnitOperations.getHumanPlayer;

public class OutroMenu extends Menu
{
    private Skin skin;
    private I18NBundle strings;
    private Table container;
    private Table summary;
    private Table details;
    private Button button;

    @Inject
    public OutroMenu(Skin skin) {
        this.skin = skin;
        this.container = createContainer();
        this.summary = createSummary();
        this.details = createDetails();
        this.button = createButton();
    }

    public Skin getSkin() {
        return skin;
    }

    public void setBackground(String name) {
        container.setBackground(name);
    }

    public void setLabelBundle(I18NBundle bundle) {
        strings = bundle;
    }

    public void setButtonAction(SelectListener action) {
        button.addListener(new SelectListenerAdapter(action));
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);

        GameEngine engine = (GameEngine)controller;
        State state = engine.getState();

        if (state != null) {
            ItemRoot world = state.getWorld();
            Player human = getHumanPlayer(world);
            Player ai = getAiPlayer(world);

            updateSummary(human);
            updateDetails(human, ai);
        }
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

        int points = getScore(player);
        String score = String.valueOf(points);
        String rank = getRank(points);

        addSummaryLabel("Outcome", "Defeat!");
        addSummaryLabel("Rank", rank);
        addSummaryLabel("Score", score);
    }

    private int getScore(Player player) {
        return 0;
    }

    private String getRank(int score) {
        return "Servant";
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
        addDetailsHeader("Units");
        addDetailsHeader("Buildings");
        addDetailsHeader("Gold");
        addDetailsHeader("Lumber");
        addDetailsHeader("Oil");
        addDetailsHeader("Kills");
        addDetailsHeaderEnd("Razings");
    }

    private Cell addDetailsHeader(String text) {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center);

        Cell cell = details.add(label);
        cell.expandX();
        cell.fillX();
        cell.padBottom(12);

        return cell;
    }

    private void addDetailsHeaderEnd(String text) {
        Cell cell = addDetailsHeader(text);
        cell.row();
    }

    private void addDetailValues(Player human, Player ai) {
        Map<PlayerStatisticType, Float> largestValues = getLargestStatistics(human, ai);
        addDetailsValues(human, largestValues);
        addDetailsTextLine(human);
        addDetailsValues(ai, largestValues);
        addDetailsTextLine(ai);
    }

    private Map<PlayerStatisticType, Float> getLargestStatistics(Player... players) {
        Map<PlayerStatisticType, Float> result = new HashMap<>();
        for (PlayerStatisticType statistic: PlayerStatisticType.values()) {
            float maximum = 0;
            for (Player player: players) {
                maximum = Math.max(maximum, player.getStatistic(statistic));
            }
            result.put(statistic, maximum);
        }
        return result;
    }

    private void addDetailsValues(Player player, Map<PlayerStatisticType, Float> largestValues) {
        for (PlayerStatisticType statistic: PlayerStatisticType.values()) {
            addDetailsValue(player.getStatistic(statistic), largestValues.get(statistic));
        }
        details.row();
    }

    private void addDetailsValue(float value, float max) {
        TextProgressBar bar = new TextProgressBar(0, max, 1, "2", skin, "progress-outro");
        bar.setValue(value);
        bar.setText(String.valueOf(value));

        Cell cell = details.add(bar);
        cell.align(Align.center);
        cell.width(94);
        cell.height(28);
        cell.padLeft(8);
        cell.padRight(8);
    }

    private void addDetailsTextLine(Player player){
        String text = player.getDescription();
        text += player.isCorporeal() ? " - You" : "- Enemy";

        Label textLine = new Label(text, skin, "font-large");
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
        TextButton result = new TextButton("Continue", skin);

        Cell cell = container.add(result);
        cell.align(Align.right);
        cell.pad(20);
        container.row();

        return result;
    }
}
