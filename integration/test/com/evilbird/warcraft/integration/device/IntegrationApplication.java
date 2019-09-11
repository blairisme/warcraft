/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.headless.HeadlessApplicationLogger;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.badlogic.gdx.backends.headless.HeadlessNativesLoader;
import com.badlogic.gdx.backends.headless.HeadlessNet;
import com.badlogic.gdx.backends.headless.HeadlessPreferences;
import com.badlogic.gdx.backends.headless.mock.audio.MockAudio;
import com.badlogic.gdx.backends.headless.mock.input.MockInput;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * @author Blair Butterworth
 */
public class IntegrationApplication implements Application
{
    protected ApplicationListener listener;
    protected ApplicationLogger applicationLogger;
    protected HeadlessFiles files;
    protected HeadlessNet net;
    protected MockAudio audio;
    protected MockInput input;
    protected MockGraphics graphics;
    protected int logLevel;
    protected float renderInterval;
    protected String preferencesdir;
    protected ObjectMap<String, Preferences> preferences;
    protected Array<Runnable> runnables;
    protected Array<Runnable> executedRunnables;
    protected Array<LifecycleListener> lifecycleListeners;

    public IntegrationApplication(ApplicationListener listener) {
        this(listener, new HeadlessApplicationConfiguration());
    }

    public IntegrationApplication(ApplicationListener listener, HeadlessApplicationConfiguration configuration) {
        this.files = new HeadlessFiles();
        this.net = new HeadlessNet();
        this.graphics = new MockGraphics();
        this.audio = new MockAudio();
        this.input = new MockInput();
        this.listener = listener;
        this.runnables = new Array<>();
        this.executedRunnables = new Array<>();
        this.lifecycleListeners = new Array<>();
        this.logLevel = LOG_INFO;
        this.preferences = new ObjectMap<>();
        this.preferencesdir = configuration.preferencesDirectory;
        this.renderInterval = configuration.renderInterval;
        initializeLibGdx();
    }

    private void initializeLibGdx() {
        HeadlessNativesLoader.load();
        setApplicationLogger(new HeadlessApplicationLogger());
        Gdx.app = this;
        Gdx.files = files;
        Gdx.net = net;
        Gdx.audio = audio;
        Gdx.graphics = graphics;
        Gdx.input = input;
        Gdx.gl = new MockGraphicsLibrary();
        Gdx.gl20 = Gdx.gl;
    }

    public void create() {
        listener.create();
    }

    public void updateCycle(int cycles) {
        for (int i = 0; i < cycles; i++) {
            update();
        }
    }

    public void update() {
        update(renderInterval);
    }

    public void update(float time) {
        executeRunnables();
        graphics.incrementFrameId();
        graphics.setDeltaTime(time);
        listener.render();
    }

    public boolean executeRunnables () {
        synchronized (runnables) {
            for (int i = runnables.size - 1; i >= 0; i--)
                executedRunnables.add(runnables.get(i));
            runnables.clear();
        }
        if (executedRunnables.size == 0) return false;
        for (int i = executedRunnables.size - 1; i >= 0; i--)
            executedRunnables.removeIndex(i).run();
        return true;
    }

    @Override
    public ApplicationListener getApplicationListener() {
        return listener;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public Files getFiles() {
        return files;
    }

    @Override
    public Net getNet() {
        return net;
    }

    @Override
    public ApplicationType getType() {
        return ApplicationType.HeadlessDesktop;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public long getJavaHeap () {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override
    public long getNativeHeap () {
        return getJavaHeap();
    }

    @Override
    public Preferences getPreferences(String name) {
        if (preferences.containsKey(name)) {
            return preferences.get(name);
        } else {
            Preferences prefs = new HeadlessPreferences(name, this.preferencesdir);
            preferences.put(name, prefs);
            return prefs;
        }
    }

    @Override
    public Clipboard getClipboard () {
        return null;
    }

    @Override
    public void postRunnable (Runnable runnable) {
        synchronized (runnables) {
            runnables.add(runnable);
        }
    }

    @Override
    public void debug (String tag, String message) {
        if (logLevel >= LOG_DEBUG) getApplicationLogger().debug(tag, message);
    }

    @Override
    public void debug (String tag, String message, Throwable exception) {
        if (logLevel >= LOG_DEBUG) getApplicationLogger().debug(tag, message, exception);
    }

    @Override
    public void log (String tag, String message) {
        if (logLevel >= LOG_INFO) getApplicationLogger().log(tag, message);
    }

    @Override
    public void log (String tag, String message, Throwable exception) {
        if (logLevel >= LOG_INFO) getApplicationLogger().log(tag, message, exception);
    }

    @Override
    public void error (String tag, String message) {
        if (logLevel >= LOG_ERROR) getApplicationLogger().error(tag, message);
    }

    @Override
    public void error (String tag, String message, Throwable exception) {
        if (logLevel >= LOG_ERROR) getApplicationLogger().error(tag, message, exception);
    }

    @Override
    public void setLogLevel (int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public int getLogLevel() {
        return logLevel;
    }

    @Override
    public void setApplicationLogger (ApplicationLogger applicationLogger) {
        this.applicationLogger = applicationLogger;
    }

    @Override
    public ApplicationLogger getApplicationLogger () {
        return applicationLogger;
    }

    @Override
    public void exit () {
    }

    @Override
    public void addLifecycleListener (LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.add(listener);
        }
    }

    @Override
    public void removeLifecycleListener (LifecycleListener listener) {
        synchronized (lifecycleListeners) {
            lifecycleListeners.removeValue(listener, true);
        }
    }
}