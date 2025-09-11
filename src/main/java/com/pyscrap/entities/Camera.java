package com.pyscrap.entities;

import com.pyscrap.input.Mouse;
import com.pyscrap.input.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    private float cameraSpeed = 0.1f;

    public Camera() {
        Mouse.createCallbacks();
    }

    public void move(){
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            position.z -= cameraSpeed * Math.cos(Math.toRadians(yaw));
            position.x += cameraSpeed * Math.sin(Math.toRadians(yaw));
        }

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.z += cameraSpeed * Math.cos(Math.toRadians(yaw));
            position.x -= cameraSpeed * Math.sin(Math.toRadians(yaw));
        }

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            position.x -= cameraSpeed * Math.cos(Math.toRadians(yaw));
            position.z -= cameraSpeed * Math.sin(Math.toRadians(yaw));
        }

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.x += cameraSpeed * Math.cos(Math.toRadians(yaw));
            position.z += cameraSpeed * Math.sin(Math.toRadians(yaw));
        }

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) {   // Go up
            position.y += cameraSpeed;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {  // Go down
            position.y -= cameraSpeed;
        }

        //System.out.println("DX:" + Mouse.getMouseDX() + "\nDY:" + Mouse.getMouseDY());

        yaw += Mouse.getMouseDX() * 0.1f;
        pitch -= Mouse.getMouseDY() * 0.1f;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
