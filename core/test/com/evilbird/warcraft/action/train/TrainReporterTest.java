/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.train;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Event;
import com.evilbird.test.testcase.ActionReporterTestCase;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.create.CreateObserver;
import com.evilbird.warcraft.action.common.transfer.TransferEvent;
import com.evilbird.warcraft.action.common.transfer.TransferObserver;

import java.util.Map;

/**
 * Instances of this unit test validate the {@link TrainReporter} class.
 *
 * @author Blair Butterworth
 */
public class TrainReporterTest extends ActionReporterTestCase
{
    @Override
    protected Class<?> getReporterType() {
        return TrainReporter.class;
    }

    @Override
    protected Map<Class<?>, Class<? extends Event>> getReportedTypes() {
        return Maps.of(CreateObserver.class, CreateEvent.class,
            TrainObserver.class, TrainEvent.class,
            TransferObserver.class, TransferEvent.class);
    }
}