/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.google.gson.annotations.JsonAdapter;

import java.util.HashSet;

/**
 * Instances of this class represent a set of {@link Identifier Identifiers}.
 *
 * @author Blair Butterworth
 */
@JsonAdapter(IdentifierSetAdapter.class)
public class IdentifierSet extends HashSet<Identifier> {
}
