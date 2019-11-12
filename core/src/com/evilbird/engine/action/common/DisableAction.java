/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.action.common;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.spatial.GameObjectGraph;
import com.evilbird.warcraft.item.common.capability.SelectableObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;

import static com.evilbird.engine.action.common.ActionUtils.getRecipient;

/**
 * Instances of this class apply the disabled status of an {@link GameObject}.
 *
 * @author Blair Butterworth
 */
public class DisableAction extends BasicAction
{
    private boolean disabled;
    private ActionRecipient recipient;

    @Inject
    public DisableAction() {
        this(true);
    }

    public DisableAction(boolean disabled) {
        this(ActionRecipient.Subject, disabled);
    }

    public DisableAction(ActionRecipient recipient, boolean disabled) {
        this.recipient = recipient;
        this.disabled = disabled;
    }

    public static DisableAction enable() {
        return new DisableAction(false);
    }

    public static DisableAction enable(ActionRecipient recipient) {
        return new DisableAction(recipient, false);
    }

    public static DisableAction disable() {
        return new DisableAction(true);
    }

    public static DisableAction disable(ActionRecipient recipient) {
        return new DisableAction(recipient, true);
    }

    @Override
    public boolean act(float delta) {
        GameObject subject = getRecipient(this, recipient);
        setSpatial(subject, disabled);
        setEnablement(subject, disabled);
        return true;
    }

    private void setSpatial(GameObject subject, boolean disabled) {
        GameObjectContainer root = subject.getRoot();
        GameObjectGraph graph = root.getSpatialGraph();

        if (disabled) {
            graph.removeOccupants(subject);
        } else {
            graph.addOccupants(subject);
        }
    }

    private void setEnablement(GameObject subject, boolean disabled) {
        SelectableObject selectable = (SelectableObject)subject;
        selectable.setSelectable(!disabled);
        subject.setTouchable(disabled ? Touchable.disabled : Touchable.enabled);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        DisableAction that = (DisableAction)obj;
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(disabled, that.disabled)
            .append(recipient, that.recipient)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .appendSuper(super.hashCode())
            .append(disabled)
            .append(recipient)
            .toHashCode();
    }
}
