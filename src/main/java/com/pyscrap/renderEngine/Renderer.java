package com.pyscrap.renderEngine;

import static org.lwjgl.opengl.GL40.*;

import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.shaders.StaticShader;
import com.pyscrap.toolbox.Maths;
import org.joml.Matrix4f;

import java.util.List;
import java.util.Map;

public class Renderer {
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 100f;

    private Matrix4f projectionMatrix;
    private StaticShader shader;

    public Renderer(StaticShader shader) {
        createProjectionMatrix();
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void prepare() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0, 1, 1, 1);
    }

    public void render(Map<TexturedModel, List<Entity>> entities) {
        for (TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);

            for (Entity entity : batch) {
                prepareInstance(entity);
                glDrawElements(GL_TRIANGLES, model.getRawModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            }

            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        glBindVertexArray(rawModel.getVaoID());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getTexture().getID());
    }

    private void unbindTexturedModel() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotation(),
                entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    private void createProjectionMatrix() {
        projectionMatrix = new Matrix4f().perspective((float) Math.toRadians(FOV),
                DisplayManager.getWindowWidth() / DisplayManager.getWindowHeight(), NEAR_PLANE, FAR_PLANE);
    }
}
