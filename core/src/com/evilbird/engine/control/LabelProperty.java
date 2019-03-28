/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.control;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Instances of this class update a {@link Label} when a given value changes.
 *
 * @author Blair Butterworth
 */
public class LabelProperty
{
    private Label label;
    private Float value;
    private Supplier<Float> valueSupplier;
    private Function<Float, String> textSupplier;

    public LabelProperty(Label label, Supplier<Float> value, Function<Float, String> text) {
        this.label = label;
        this.value = -1f;
        this.valueSupplier = value;
        this.textSupplier = text;
    }

    public void evaluate() {
        Float newValue = valueSupplier.get();
        if (!Objects.equals(newValue, value)) {
            value = newValue;
            label.setText(textSupplier.apply(value));
        }
    }
}
