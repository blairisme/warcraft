package com.evilbird.warcraft.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.warcraft.device.DeviceInput;
import com.evilbird.warcraft.device.UserInput;
import com.evilbird.warcraft.device.UserInputType;
import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.lwjgl.opengl.Display;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DesktopInput implements DeviceInput, GestureDetector.GestureListener
{
    private List<com.evilbird.warcraft.device.UserInput> inputs;

    public DesktopInput()
    {
        inputs = new ArrayList<com.evilbird.warcraft.device.UserInput>();
    }

    @Override
    public void install()
    {
        GestureDetector gestureDetector = new GestureDetector(this);
        Gdx.input.setInputProcessor(gestureDetector);


        long window = getWindowHandle();
        //System.out.println(windowHandle);


        WindowSubclassProc windowProc = new WindowSubclassProc()
        {

            @Override
            public LRESULT callback(HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam)
            {
                return null;
            }
        };

        HWND windowHandle = new HWND(Pointer.createConstant(window));

        MyUser32.MYINSTANCE.SetWindowLong(windowHandle, MyUser32.GWLP_WNDPROC,  windowProc);
    }

    public interface MyUser32 extends User32
    {
        public static final MyUser32 MYINSTANCE = (MyUser32) Native.loadLibrary("user32", MyUser32.class, W32APIOptions.UNICODE_OPTIONS);

        /**
         * Sets a new address for the window procedure (value to be set).
         */
        public static final int GWLP_WNDPROC = -4;

        /**
         * Changes an attribute of the specified window
         * @param   hWnd        A handle to the window
         * @param   nIndex      The zero-based offset to the value to be set.
         * @param   callback    The callback function for the value to be set.
         */
        public int SetWindowLong(WinDef.HWND hWnd, int nIndex, Callback callback);
    }

    public interface WindowSubclassProc extends StdCallLibrary.StdCallCallback
    {
        public LRESULT callback(HWND hWnd, int uMsg, WPARAM uParam, LPARAM lParam);
    }

    private long getWindowHandle()
    {
        try
        {
            Method method = MethodUtils.getMatchingMethod(Display.class, "getImplementation");
            method.setAccessible(true);

            Object implementation = method.invoke(null);
            Object handle = FieldUtils.readField(implementation, "hwnd", true);

            return (Long)handle;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<UserInput> readInput()
    {
        List<com.evilbird.warcraft.device.UserInput> result = new ArrayList<UserInput>(inputs);
        inputs.clear();
        return result;
    }

    private synchronized void pushInput(com.evilbird.warcraft.device.UserInput userInput)
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
