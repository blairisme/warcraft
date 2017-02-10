package com.evilbird.engine.device;

import java.util.List;

public interface DeviceInput
{
    void install();

    List<UserInput> readInput();
}
