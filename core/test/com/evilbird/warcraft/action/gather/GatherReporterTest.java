/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.events.Event;
import com.evilbird.test.testcase.ActionReporterTestCase;
import com.evilbird.warcraft.action.common.resource.ResourceTransferEvent;
import com.evilbird.warcraft.action.common.resource.ResourceTransferObserver;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.action.move.MoveObserver;
import com.evilbird.warcraft.action.select.SelectEvent;
import com.evilbird.warcraft.action.select.SelectObserver;

import java.util.Map;

/**
 * Instances of this unit test validate the {@link GatherReporter} class.
 *
 * @author Blair Butterworth
 */
public class GatherReporterTest extends ActionReporterTestCase
{
    @Override
    protected Class<?> getReporterType() {
        return GatherReporter.class;
    }

    @Override
    protected Map<Class<?>, Class<? extends Event>> getReportedTypes() {
        return Maps.of(GatherObserver.class, GatherEvent.class,
            MoveObserver.class, MoveEvent.class,
            SelectObserver.class, SelectEvent.class,
            ResourceTransferObserver.class, ResourceTransferEvent.class);
    }
}