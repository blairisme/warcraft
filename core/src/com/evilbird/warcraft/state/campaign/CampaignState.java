/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.campaign;

import com.evilbird.engine.behaviour.Behaviour;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.menu.MenuIdentifier;
import com.evilbird.engine.state.IntroducedState;
import com.evilbird.engine.state.StateIdentifier;
import com.evilbird.engine.state.StateSequence;
import com.evilbird.warcraft.common.WarcraftContext;
import com.evilbird.warcraft.state.scenario.ScenarioState;
import com.google.gson.annotations.JsonAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * <p>
 * A ScenarioState specialization that specifies an introduction menu
 * and the scenario to load when the scenario is complete.
 * </p>
 * <p>
 * WarcraftCampaignStates represent a game state, a snapshot of the all game
 * objects and their properties at a given point in time. Warcraft game state
 * is persisted for objects in the game world, but not the user interface or
 * active behaviours which are stateless.
 * </p>
 * @author Blair Butterworth
 */
@JsonAdapter(CampaignAdapter.class)
public class CampaignState extends ScenarioState implements StateSequence, IntroducedState
{
    private MenuIdentifier intro;
    private StateIdentifier next;

    public CampaignState() {
    }

    public CampaignState(
        ItemRoot world,
        ItemRoot hud,
        Behaviour behaviour,
        WarcraftContext context,
        MenuIdentifier intro,
        StateIdentifier next)
    {
        super(world, hud, behaviour, context);
        this.intro = intro;
        this.next = next;
    }

    @Override
    public MenuIdentifier getIntroductionMenu() {
        return intro;
    }

    @Override
    public StateIdentifier getNextState() {
        return next;
    }

    public void setIntroductionMenu(MenuIdentifier intro) {
        this.intro = intro;
    }

    public void setNextState(StateIdentifier next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        CampaignState that = (CampaignState)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(intro, that.intro)
            .append(next, that.next)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(intro)
            .append(next)
            .toHashCode();
    }
}
