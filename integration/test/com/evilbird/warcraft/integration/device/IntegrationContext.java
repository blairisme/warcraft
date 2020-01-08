/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.integration.device;

import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.game.GameService;
import com.evilbird.warcraft.integration.device.DaggerIntegrationInjector;
import com.evilbird.warcraft.integration.device.DaggerIntegrationInjector.Builder;

/**
 * Instances of this class represent the entry point for the desktop version of
 * the application.
 *
 * @author Blair Butterworth
 */
public class IntegrationContext
{
    private IntegrationApplication application;
    private GameService service;
    private GameEngine engine;

    public IntegrationContext() {
        service = newService();
        engine = service.getEngine();
        application = new IntegrationApplication(engine);
        application.create();
    }

    public IntegrationApplication getApplication() {
        return application;
    }

    public GameEngine getEngine() {
        return engine;
    }

    public GameService getService() {
        return service;
    }

    private GameService newService() {
        GameService service = GameService.getInstance();
        service.setInjector(newInjector());
        return service;
    }

    private IntegrationInjector newInjector() {
        Builder builder = DaggerIntegrationInjector.builder();
        return builder.build();
    }
}
