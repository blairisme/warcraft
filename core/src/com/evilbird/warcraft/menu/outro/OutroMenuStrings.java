/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.outro;

import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.warcraft.object.data.player.Player;

import static com.evilbird.warcraft.menu.outro.OutroMenuType.Victory;

/**
 * A language specific bundle of strings used in outro menus.
 *
 * @see <a href="http://classic.battle.net/war2/basic/score.shtml">Warcraft Scoring</a>
 *
 * @author Blair Butterworth
 */
public class OutroMenuStrings
{
    private I18NBundle outroBundle;
    private I18NBundle nationsBundle;

    public OutroMenuStrings(I18NBundle outroBundle, I18NBundle nationsBundle) {
        this.outroBundle = outroBundle;
        this.nationsBundle = nationsBundle;
    }

    public String getOutcome(OutroMenuType type) {
        return outroBundle.get(type == Victory ? "outcome-victory" : "outcome-defeat");
    }

    public String getOutcomeLabel() {
        return outroBundle.get("outcome-label");
    }

    public String getRank(int score) {
        if (score >= 0 && score <= 2000) {
            return outroBundle.get("rank-first-human");
        }
        else if (score >= 2001 && score <= 5000) {
            return outroBundle.get("rank-second-human");
        }
        return outroBundle.get("rank-last-human");
    }

    public String getRankLabel() {
        return outroBundle.get("rank-label");
    }

    public String getScoreLabel() {
        return outroBundle.get("score-label");
    }

    public String getPlayerName(Player player) {
        String nation = nationsBundle.get(player.getNation().name());
        return outroBundle.format(player.isCorporeal() ? "player-you" : "player-them", nation);
    }

    public String getButtonLabel() {
        return outroBundle.get("button-label");
    }

    public String getUnitLabel() {
        return outroBundle.get("header-unit");
    }

    public String getBuildingsLabel() {
        return outroBundle.get("header-buildings");
    }

    public String getGoldLabel() {
        return outroBundle.get("header-gold");
    }

    public String getWoodLabel() {
        return outroBundle.get("header-wood");
    }

    public String getOilLabel() {
        return outroBundle.get("header-oil");
    }

    public String getKillsLabel() {
        return outroBundle.get("header-kills");
    }

    public String getRazedLabel() {
        return outroBundle.get("header-razed");
    }
}