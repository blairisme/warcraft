import os, sys, math
import struct, base64, zlib

import win32gui
import win32con
import win32api

import time
import re
import ctypes

from pykeyboard import PyKeyboard

from PIL import Image
from PIL import ImageGrab


class WindowManager:
    def __init__(self):
        self._handle = None

    def find_window(self, class_name, window_name=None):
        self._handle = win32gui.FindWindow(class_name, window_name)

    def _window_enum_callback(self, hwnd, wildcard):
        if re.match(wildcard, str(win32gui.GetWindowText(hwnd))) is not None:
            self._handle = hwnd

    def find_window_wildcard(self, wildcard):
        self._handle = None
        win32gui.EnumWindows(self._window_enum_callback, wildcard)

    def set_foreground_window(self):
        win32gui.SetForegroundWindow(self._handle)

    def get_bounds(self):
        return win32gui.GetWindowRect(self._handle)

    def get_title(self):
        return win32gui.GetWindowText(self._handle)

    def post_key(self, key):
        win32api.SendMessage(self._handle, win32con.WM_KEYDOWN, key, 0)
        win32api.SendMessage(self._handle, win32con.WM_KEYUP, key, 0)

    def is_foreground_window(self):
        return win32gui.GetForegroundWindow() == self._handle


def rectangle(x, y, width, height):
    return (x,y,x+width,y+height)


def image_equals(source, target):
    if source is None or target is None:
        return False

    source_width, source_height = source.size
    target_width, target_height = target.size

    if source_width != target_width:
        print("diff widths")
        return False

    if source_height != target_height:
        print("diff heights")
        return False

    for x in range(0, source_width):
        for y in range(0, source_height):
            position = (x, y)
            if source.getpixel(position) != target.getpixel(position):
                print("diff pixels")
                return False

    return True


def main():
    ctypes.windll.user32.SetProcessDPIAware()

    keyboard = PyKeyboard()
    window = WindowManager()
    window.find_window_wildcard(".*DOSBox 0.*")

    window_bounds = window.get_bounds()
    capture_bounds = rectangle(window_bounds[0] + 179, window_bounds[1] + 64, 448, 448)

    counter = 1
    sequence_length = 30
    key = keyboard.down_key

    if not os.path.exists("captures"):
        os.makedirs("captures")

    while True:
        time.sleep(0.5)

        if window.is_foreground_window():
            image = ImageGrab.grab(bbox=capture_bounds).convert("RGB")
            image.save("captures/capture" + str(counter) + ".png", "PNG")

            if counter % sequence_length == 0:
                keyboard.press_key(keyboard.right_key)
                time.sleep(0.05)
                keyboard.release_key(keyboard.right_key)

                if key is keyboard.down_key:
                    key = keyboard.up_key
                else:
                    key = keyboard.down_key

            else:
                keyboard.press_key(key)
                time.sleep(0.05)
                keyboard.release_key(key)

            counter += 1


if __name__ == "__main__":
    main()
