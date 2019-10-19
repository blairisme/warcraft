/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.item.Item;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

/**
 * Instances of this class define the different ways the user can interact with
 * the game and the actions that result from these interactions.
 *
 * @author Blair Butterworth
 */
public class Interactions
{
    private InteractionContainer interactions;
    private Cache<InteractionQuery, InteractionQueryResult> cache;

    @Inject
    public Interactions(
        InteractionContainer interactions,
        AttackInteractions attackInteractions,
        CameraInteractions cameraInteractions,
        CheatInteractions cheatInteractions,
        ConstructInteractions constructInteractions,
        GatherInteractions gatherInteractions,
        MenuInteractions menuInteractions,
        MoveInteractions moveInteractions,
        ProduceInteractions produceInteractions,
        SelectInteractions selectInteractions,
        TransportInteractions transportInteractions)
    {
        this.interactions = interactions;
        this.interactions.addActions(moveInteractions);
        this.interactions.addActions(attackInteractions);
        this.interactions.addActions(gatherInteractions);
        this.interactions.addActions(constructInteractions);
        this.interactions.addActions(produceInteractions);
        this.interactions.addActions(transportInteractions);
        this.interactions.addActions(menuInteractions);
        this.interactions.addActions(cameraInteractions);
        this.interactions.addActions(selectInteractions);
        this.interactions.addActions(cheatInteractions);
        this.cache = Caffeine.newBuilder().maximumSize(100).build();
    }

    /**
     * Returns the first interaction that applies to the given user input, target
     * item and selected item. The specific matching algorithm is contained in
     * the individual {@link Interaction} implementations.
     *
     * @param input     a {@link UserInput} instance specifying the interaction
     *                  the user made with the application
     * @param item      the target of the users interaction.
     * @param selected  a currently selected item.
     *
     * @return the matching {@code Interaction}, if any.
     */
    public Interaction getInteraction(UserInput input, Item item, Item selected) {
        InteractionQuery query = new InteractionQuery(input, item, selected);
        InteractionQueryResult result = cache.getIfPresent(query);
        if (result == null) {
            Interaction interaction = interactions.getInteraction(input, item, selected);
            result = new InteractionQueryResult(interaction);
            cache.put(query, result);
        }
        return result.getValue();
    }

    private static class InteractionQueryResult {
        private Interaction value;

        public InteractionQueryResult(Interaction value) {
            this.value = value;
        }

        public Interaction getValue() {
            return value;
        }
    }

    private static class InteractionQuery {
        private UserInputType type;
        private Item target;
        private Item selected;

        public InteractionQuery(UserInput input, Item target, Item selected) {
            this.type = input.getType();
            this.target = target;
            this.selected = selected;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) { return false; }
            if (object == this) { return true; }
            if (object.getClass() != getClass()) { return false; }

            InteractionQuery that = (InteractionQuery)object;
            return new EqualsBuilder()
                .append(type, that.type)
                .append(target, that.target)
                .append(selected, that.selected)
                .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                .append(type)
                .append(target)
                .append(selected)
                .toHashCode();
        }
    }
}
