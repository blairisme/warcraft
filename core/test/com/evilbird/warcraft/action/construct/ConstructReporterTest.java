/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.construct;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Event;
import com.evilbird.test.testcase.ActionReporterTestCase;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.create.CreateObserver;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.common.remove.RemoveObserver;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.common.resource.ResourceTransferObserver;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.move.MoveObserver;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.select.SelectObserver;

import java.util.Map;

/**
 * Instances of this unit test validate the {@link ConstructReporter} class.
 *
 * @author Blair Butterworth
 */
public class ConstructReporterTest extends ActionReporterTestCase
{
    @Override
    protected Class<?> getReporterType() {
        return ConstructReporter.class;
    }

    @Override
    protected Map<Class<?>, Class<? extends Event>> getReportedTypes() {
        return Maps.of(ConstructObserver.class, ConstructEvent.class,
            CreateObserver.class, CreateEvent.class,
            MoveObserver.class, MoveEvent.class,
            RemoveObserver.class, RemoveEvent.class,
            SelectObserver.class, SelectEvent.class,
            ResourceTransferObserver.class, ResourceTransferEvent.class);
    }
}