/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
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
