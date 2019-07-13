/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state;

import com.evilbird.engine.state.State;
import com.google.gson.annotations.JsonAdapter;

/**
 * Instances of class represent a snapshot of all Warcraft game
 * objects and their properties at a given point in time.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(WarcraftStateAdapter.class)
public interface WarcraftState extends State
{
}
