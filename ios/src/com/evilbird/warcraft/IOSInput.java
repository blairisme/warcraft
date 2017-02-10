package com.evilbird.warcraft;

import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;

import java.util.Collections;
import java.util.List;

public class IOSInput implements DeviceInput
{
    @Override
    public void install()
    {
        //TODO: Install input listener
    }

    @Override
    public List<UserInput> readInput()
    {
        return Collections.emptyList(); //TODO: Return input
    }
}
