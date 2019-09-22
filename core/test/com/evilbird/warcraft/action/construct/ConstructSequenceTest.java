/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.action.Action;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.common.associate.AssociateAction;
import com.evilbird.warcraft.action.common.create.CreateAction;
import com.evilbird.warcraft.action.common.remove.RemoveOccluding;
import com.evilbird.warcraft.action.common.remove.RemoveTarget;
import com.evilbird.warcraft.action.common.transfer.TransferAction;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.action.select.DeselectAction;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link ConstructSequence} class.
 *
 * @author Blair Butterworth
 */
public class ConstructSequenceTest extends ActionTestCase
{
    @Override
    protected Action newAction() {
        AssociateAction associate = Mockito.mock(AssociateAction.class);
        ConstructAction construct = Mockito.mock(ConstructAction.class);
        CreateAction create = Mockito.mock(CreateAction.class);
        DeselectAction deselect = Mockito.mock(DeselectAction.class);
        MoveToItemAction move = Mockito.mock(MoveToItemAction.class);
        RemoveOccluding removeCorpses = Mockito.mock(RemoveOccluding.class);
        RemoveTarget removePlaceholder = Mockito.mock(RemoveTarget.class);
        TransferAction purchase = Mockito.mock(TransferAction.class);
        TransferAction transfer = Mockito.mock(TransferAction.class);

        ConstructSequence action = new ConstructSequence(
            associate, construct, create, deselect, move,
            removeCorpses, removePlaceholder, purchase, transfer);
        action.setIdentifier(ConstructActions.ConstructBarracks);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ConstructActions.ConstructBarracks;
    }
}