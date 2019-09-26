/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.device;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.badlogic.gdx.math.Vector2.Zero;

/**
 * Encapsulates data about a single user interaction with the application.
 * Methods are provided to determine the type of interaction, dragging,
 * tapping, etc, as well the location on the screen where the input was made.
 *
 * @author Blair Butterworth
 */
public class UserInput
{
    private UserInputType type;
    private Vector2 position;
    private Vector2 delta;
    private int key;
    private int count;

    public UserInput(UserInputType type, int key) {
        this.type = type;
        this.key = key;
        this.position = Zero;
        this.delta = Zero;
        this.count = 1;
    }

    public UserInput(UserInputType type, Vector2 position, int count) {
        this(type, position, Zero, count);
    }

    public UserInput(UserInputType type, Vector2 position, Vector2 delta, int count) {
        this.type = type;
        this.key = Input.Keys.ANY_KEY;
        this.position = position;
        this.delta = delta;
        this.count = count;
    }

    public UserInputType getType() {
        return type;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getDelta() {
        return this.delta;
    }

    public int getCount() {
        return this.count;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("type", type)
            .append("position", position)
            .append("delta", delta)
            .append("count", count)
            .append("key", key)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        UserInput userInput = (UserInput)obj;
        return new EqualsBuilder()
            .append(count, userInput.count)
            .append(type, userInput.type)
            .append(position, userInput.position)
            .append(delta, userInput.delta)
            .append(key, userInput.key)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(type)
            .append(position)
            .append(delta)
            .append(count)
            .append(key)
            .toHashCode();
    }
}
