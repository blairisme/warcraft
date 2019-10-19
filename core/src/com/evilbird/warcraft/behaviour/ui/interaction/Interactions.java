/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Instances of this class define the different ways the user can interact with
 * the game and the actions that result from these interactions.
 *
 * @author Blair Butterworth
 */
public class Interactions extends InteractionContainer
{
    @Inject
    public Interactions(
        Provider<InteractionDefinition> factory,
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
        super(factory);
        addActions(moveInteractions);
        addActions(attackInteractions);
        addActions(gatherInteractions);
        addActions(constructInteractions);
        addActions(produceInteractions);
        addActions(transportInteractions);
        addActions(menuInteractions);
        addActions(cameraInteractions);
        addActions(selectInteractions);
        addActions(cheatInteractions);
    }
}
