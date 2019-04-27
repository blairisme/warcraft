/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.device.DeviceInput;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;

import java.util.ArrayList;
import java.util.List;

// TODO: Finish zoom implementation
public class AndroidInput implements DeviceInput, GestureDetector.GestureListener
{
    private List<UserInput> inputs;
    private int panCount;
    private int zoomCount;

    public AndroidInput() {
        inputs = new ArrayList<>();
    }

    @Override
    public void install() {
        GestureDetector gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public List<UserInput> readInput() {
        List<UserInput> result = new ArrayList<>(inputs);
        inputs.clear();
        return result;
    }

    private synchronized void pushInput(UserInput userInput) {
        inputs.add(userInput);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        UserInput input = new UserInput(UserInputType.Action, new Vector2(x, y), 1);
        pushInput(input);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        UserInput input = new UserInput(UserInputType.Drag, new Vector2(x, y), new Vector2(deltaX * -1, deltaY), panCount++);
        pushInput(input);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        panCount = 1;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float scale = initialDistance / distance;
        Vector2 delta = new Vector2(scale, scale);

        UserInput input = new UserInput(UserInputType.Zoom, Vector2.Zero, delta, zoomCount++);
        pushInput(input);

        return true;
    }

//    private boolean pinching = false;
//    private int count = 1;

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
//        if (!pinching) {
//            pinching = true;
//            count = 1;
//
//            float pointerCenterX = (pointer1.x + pointer2.x)/2;
//            float pointerCenterY = (pointer1.y+ pointer2.y)/2;
//            float screenCenterX = graphics.getWidth()/2;
//            float screenCenterY = graphics.getHeight();
//            float panX = screenCenterX - pointerCenterX;
//            float panY = screenCenterY - pointerCenterY-1;
//            Vector2 delta = new Vector2(panX, panY);
//            UserInput input = new UserInput(UserInputType.Drag, Vector2.Zero, delta, 1);
//            pushInput(input);
//
//        }
        return false;
    }

    @Override
    public void pinchStop() {
        zoomCount = 1;
//        pinching = false;
    }
}