/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.warcraft.item.data.player.Player;

import static com.evilbird.warcraft.menu.outro.OutroMenuType.Victory;

/**
 * http://classic.battle.net/war2/basic/score.shtml
 *
 * @author Blair Butterworth
 */
public class OutroMenuStrings
{
    private I18NBundle bundle;

    public OutroMenuStrings(I18NBundle bundle) {
        this.bundle = bundle;
    }

    public String getOutcome(OutroMenuType type) {
        return bundle.get(type == Victory ? "outcome-victory" : "outcome-defeat");
    }

    public String getOutcomeLabel() {
        return bundle.get("outcome-label");
    }

    public String getRank(int score) {
        if (score >= 0 && score <= 2000) {
            return bundle.get("rank-first-human");
        }
        else if (score >= 2001 && score <= 5000) {
            return bundle.get("rank-second-human");
        }
        return bundle.get("rank-last-human");
    }

    public String getRankLabel() {
        return bundle.get("rank-label");
    }

    public String getScoreLabel() {
        return bundle.get("score-label");
    }

    public String getPlayerName(Player player) {
        StringBuilder builder = new StringBuilder();
        builder.append(player.getDescription());
        builder.append(bundle.get(player.isCorporeal() ? "player-you" : "player-them" ));
        return builder.toString();
    }

    public String getButtonLabel() {
        return bundle.get("button-label");
    }

    public String getUnitLabel() {
        return bundle.get("header-unit");
    }

    public String getBuildingsLabel() {
        return bundle.get("header-buildings");
    }

    public String getGoldLabel() {
        return bundle.get("header-gold");
    }

    public String getWoodLabel() {
        return bundle.get("header-wood");
    }

    public String getOilLabel() {
        return bundle.get("header-oil");
    }

    public String getKillsLabel() {
        return bundle.get("header-kills");
    }

    public String getRazedLabel() {
        return bundle.get("header-razed");
    }
}