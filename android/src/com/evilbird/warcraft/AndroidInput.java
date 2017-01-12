package com.evilbird.warcraft;

import com.evilbird.warcraft.device.DeviceInput;
import com.evilbird.warcraft.device.UserInput;

import java.util.List;

public class AndroidInput implements DeviceInput
{
    @Override
    public void install()
    {

    }

    @Override
    public List<UserInput> readInput()
    {
        return null;
    }
}
