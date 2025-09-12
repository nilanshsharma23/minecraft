package com.pyscrap.input;

import com.pyscrap.renderEngine.DisplayManager;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse {
    private static float mouseX, mouseY, mouseDX, mouseDY;
    private static boolean firstMouse = true;
    private static double lastX, lastY;

    public static void createCallbacks() {
        GLFWCursorPosCallback cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                if (firstMouse) {
                    lastX = xpos;
                    lastY = ypos;
                    firstMouse = false;
                }

                mouseDX = (float) (xpos - lastX);
                mouseDY = (float) (lastY - ypos);

                mouseX = (float) xpos;
                mouseY = (float) ypos;

                lastX = xpos;
                lastY = ypos;
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
        return mouseDX;
    }

    public static float getMouseDY() {
        return mouseDY;
    }

    public static void endFrame() {
        mouseDX = 0;
        mouseDY = 0;
    }
}
