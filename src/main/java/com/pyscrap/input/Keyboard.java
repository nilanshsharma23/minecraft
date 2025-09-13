package com.pyscrap.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Keyboard extends GLFWKeyCallback {
    private static boolean[] keyDown = new boolean[65536];
    private static boolean[] keyPressed = new boolean[65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key < 0)
            return;

        keyDown[key] = action != GLFW_RELEASE;
        keyPressed[key] = action == GLFW_PRESS;
    }

    public static boolean isKeyDown(int keycode) {
        return keyDown[keycode];
    }

    public static boolean isKeyPressed(int keycode) {
        return keyPressed[keycode];
    }
}
