package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.warcraft.device.DeviceInput;
import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.device.UserInputType;

import java.util.ArrayList;
import java.util.List;

public class DesktopInput implements DeviceInput, GestureDetector.GestureListener
{
    private List<UserInput> inputs;

    public DesktopInput()
    {
        inputs = new ArrayList<UserInput>();
    }

    @Override
    public void install()
    {
        GestureDetector gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public List<UserInput> readInput()
    {
        List<UserInput> result = new ArrayList<UserInput>(inputs);
        inputs.clear();
        return result;
    }

    private synchronized void pushInput(UserInput userInput)
    {
        inputs.add(userInput);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button)
    {
        UserInput input = new UserInput(UserInputType.Action, new Vector2(x, y));
        pushInput(input);
        return true;
    }

    @Override
    public boolean longPress(float x, float y)
    {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button)
    {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY)
    {
        UserInput input = new UserInput(UserInputType.Pan, new Vector2(x, y), new Vector2(deltaX * -1, deltaY));
        pushInput(input);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance)
    {
        float scale = distance / initialDistance;
        UserInput input = new UserInput(UserInputType.Zoom, new Vector2(0, 0), new Vector2(scale, scale));
        pushInput(input);
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2)
    {
        return false;
    }

    @Override
    public void pinchStop()
    {
    }
}
