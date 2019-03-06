/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.user;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.state.State;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Instances of this class read and write saved user states from external
 * storage: game saves.
 *
 * @author Blair Butterwort
 */
public class UserStateService
{
    private static final String SAVE_PATH = "saves";

    private DeviceStorage storage;
    private Serializer serializer;

    @Inject
    public UserStateService(Device device, JsonSerializer serializer) {
        this.serializer = serializer;
        this.storage = device.getDeviceStorage();
    }

    public List<Identifier> list() throws IOException {
        List<String> persisted = storage.list(SAVE_PATH);
        return persisted.stream().map(UserState::new).collect(Collectors.toList());
    }

    public State get(UserState userState) throws IOException {
        try (Reader reader = storage.read(userState.getPath())) {
            return serializer.deserialize(reader, State.class);
        }
    }

    public void set(UserState userState, State state) throws IOException {
        try (Writer writer = storage.write(userState.getPath())) {
            serializer.serialize(state, State.class, writer);
        }
    }

    public void remove(UserState userState) throws IOException {
        storage.remove(userState.getPath());
    }
}
