package com.pyscrap.entities;

import com.pyscrap.input.Mouse;
import com.pyscrap.terrain.World;
import com.pyscrap.Globals;
import com.pyscrap.input.Keyboard;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position = new Vector3f((Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_X) / 2, 16,
            (Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z) / 2);
    private float pitch;
    private float yaw;
    private float roll;

    Boolean isColliding = false;

    public Camera() {
        Mouse.createCallbacks();
    }

    public void move(float deltaTime) {
        float cameraSpeed = 10f * deltaTime;

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

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)) { // Go up
            position.y += cameraSpeed;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) { // Go down
            position.y -= cameraSpeed;
        }

        yaw += Mouse.getMouseDX() * deltaTime * 10f;
        pitch -= Mouse.getMouseDY() * deltaTime * 10f;

        if (pitch > 90) {
            pitch = 90;
        }

        if (pitch < -90) {
            pitch = -90;
        }

        Mouse.endFrame();

        // int x = (int) Math.floor(position.x);
        // int y = (int) (Math.floor(position.y));
        // int z = (int) (Math.floor(position.z));

        // System.out.println(x + " " + y + " " + z);

        // int id = (x + ((int) (y *
        // Globals.CHUNK_HEIGHT))
        // + ((int) (z * Globals.CHUNK_LENGTH *
        // Globals.CHUNK_HEIGHT)));

        // if (World.getBlockID(id) == 1) {
        // System.out.println(x + " " + y + " " + z);
        // }

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
