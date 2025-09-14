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

    boolean positiveXCollision = false;
    boolean negativeXCollision = false;
    boolean positiveYCollision = false;
    boolean negativeYCollision = false;
    boolean positiveZCollision = false;
    boolean negativeZCollision = false;

    public Camera() {
        Mouse.createCallbacks();
    }

    public void move(float deltaTime) {
        positiveYCollision = World.getBlockID((int) Math.ceil(position.x), (int) Math.ceil(position.y),
                (int) position.z) != 0;

        negativeYCollision = World.getBlockID((int) Math.ceil(position.x), (int) Math.ceil(position.y - 2),
                (int) Math.ceil(position.z)) != 0;

        float cameraSpeed = (negativeYCollision ? 7.5f : 10f) * deltaTime;

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

        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE) && !positiveYCollision) { // Go up
            position.y += cameraSpeed;
        }
        if (Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT) && !negativeYCollision) { // Go down
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

        Globals.chunkCoordX = (int) Math.floorDiv((int) position.x, Globals.CHUNK_LENGTH);
        Globals.chunkCoordZ = (int) Math.floorDiv((int) position.z, Globals.CHUNK_LENGTH);

        // System.out.println(Globals.chunkCoordX + " " + Globals.chunkCoordY);
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
