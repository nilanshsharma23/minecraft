package com.pyscrap.input;

import com.pyscrap.renderEngine.DisplayManager;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Mouse {
    private static float mouseX, mouseY, prevMouseX, prevMouseY, deltaTime;

    public static void createCallbacks() {
        GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                prevMouseX = mouseX;
                prevMouseY = mouseY;
                mouseX = (float) xpos;
                mouseY = (float)(DisplayManager.getWindowHeight() - ypos);
            }
        };

        cursorPosCallback.set(DisplayManager.getWindow());
    }

    public static float getMouseX() {
        return mouseX;
    }

    public static float getMouseY() {
        return mouseY;
    }

    public static float getMouseDX() {
        return mouseX - prevMouseX;
    }

    public static float getMouseDY() {
        return mouseY - prevMouseY;
    }
}
