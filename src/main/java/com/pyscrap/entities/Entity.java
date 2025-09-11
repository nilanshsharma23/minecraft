package com.pyscrap.entities;

import org.joml.Vector3f;

import com.pyscrap.models.TexturedModel;

public class Entity {
    private TexturedModel model;
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void increasePosition(Float dx, Float dy, Float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void increaseRotation(Float dx, Float dy, Float dz){
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public TexturedModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }
}
