/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

/**
 * Defines options for specifying whether any actions resulting from an
 * {@link InteractionDefinition} should replace existing actions or supplement
 * them.
 *
 * @author Blair Butterworth
 */
public enum InteractionDisplacement
{
    Addition,
    Replacement,
    Singleton,
    Standalone
}
