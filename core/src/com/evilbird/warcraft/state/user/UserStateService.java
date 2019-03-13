/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.state.user;

import com.evilbird.engine.common.file.FileType;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.serialization.JsonSerializer;
import com.evilbird.engine.common.serialization.Serializer;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.device.DeviceStorage;
import com.evilbird.engine.state.State;
import org.apache.commons.io.FilenameUtils;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Instances of this class read and write saved user states from external
 * storage: game saves.
 *
 * @author Blair Butterwort
 */
public class UserStateService
{
    private static final String SAVES = "saves";
    private static final String JSON = FileType.JSON.getFileExtension();

    private DeviceStorage storage;
    private Serializer serializer;

    @Inject
    public UserStateService(Device device, JsonSerializer serializer) {
        this.serializer = serializer;
        this.storage = device.getDeviceStorage();
    }

    public List<Identifier> list() throws IOException {
        List<Identifier> result = new ArrayList<>();
        for (String path: storage.list(SAVES)) {
            result.add(toState(path));
        }
        return result;
    }

    public State get(UserStateIdentifier userStateIdentifier) throws IOException {
        try (Reader reader = storage.read(toPath(userStateIdentifier))) {
            return serializer.deserialize(reader, State.class);
        }
    }

    public void set(UserStateIdentifier userStateIdentifier, State state) throws IOException {
        try (Writer writer = storage.write(toPath(userStateIdentifier))) {
            serializer.serialize(state, State.class, writer);
        }
    }

    public void remove(UserStateIdentifier userStateIdentifier) throws IOException {
        storage.remove(toPath(userStateIdentifier));
    }

    private UserStateIdentifier toState(String path) {
        return new UserStateIdentifier(FilenameUtils.getBaseName(path));
    }

    private String toPath(UserStateIdentifier state) {
        return SAVES + File.separator + state.getName() + JSON;
    }
}
