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

@JsonAdapter(WarcraftStateAdapter.class)
public interface WarcraftState extends State
{
}
