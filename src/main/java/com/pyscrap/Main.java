package com.pyscrap;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.util.ArrayList;
import java.util.List;

import com.pyscrap.entities.Camera;
import com.pyscrap.input.Keyboard;
import com.pyscrap.renderEngine.DisplayManager;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.shaders.StaticShader;
import com.pyscrap.terrain.World;
import com.pyscrap.textures.ModelTexture;

import org.lwjgl.opengl.GL11;

public class Main {
    static float deltaTime;

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        double lastTime = glfwGetTime();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();

        List<ModelTexture> textures = new ArrayList<>();

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 4; x++) {
                textures.add(new ModelTexture(loader.loadTexture(x, y)));
            }
        }

        MasterRenderer renderer = new MasterRenderer();

        World world = new World(textures, renderer, loader);

        // float[] vertices = {
        // // -Z face
        // -1.0f, -1.0f, 1.0f,
        // 1.0f, -1.0f, 1.0f,
        // 1.0f, 1.0f, 1.0f,
        // -1.0f, 1.0f, 1.0f,

        // // +Z Face
        // -1.0f, -1.0f, -1.0f,
        // -1.0f, 1.0f, -1.0f,
        // 1.0f, 1.0f, -1.0f,
        // 1.0f, -1.0f, -1.0f,

        // // +X Face
        // 1.0f, -1.0f, 1.0f,
        // 1.0f, -1.0f, -1.0f,
        // 1.0f, 1.0f, -1.0f,
        // 1.0f, 1.0f, 1.0f,

        // // -X Face
        // -1.0f, -1.0f, 1.0f,
        // -1.0f, 1.0f, 1.0f,
        // -1.0f, 1.0f, -1.0f,
        // -1.0f, -1.0f, -1.0f,

        // // +Y Face
        // -1.0f, 1.0f, 1.0f,
        // 1.0f, 1.0f, 1.0f,
        // 1.0f, 1.0f, -1.0f,
        // -1.0f, 1.0f, -1.0f,

        // // -Y Face
        // -1.0f, -1.0f, 1.0f,
        // -1.0f, -1.0f, -1.0f,
        // 1.0f, -1.0f, -1.0f,
        // 1.0f, -1.0f, 1.0f };

        // float[] textureCoords = {
        // // -Z coords
        // 0.0f, 0.0f,
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,

        // // +Z Face
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,
        // 0.0f, 0.0f,

        // // +X Face
        // 0.0f, 0.0f,
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,

        // // -X Face
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,
        // 0.0f, 0.0f,

        // // +Y Face
        // 0.0f, 0.0f,
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,

        // // -Y Face
        // 1.0f, 0.0f,
        // 1.0f, 1.0f,
        // 0.0f, 1.0f,
        // 0.0f, 0.0f };

        // int[] indices = {
        // 0, 1, 2, // first triangle
        // 2, 3, 0, // second triangle

        // // Back Face (using vertices 4-7)
        // 4, 5, 6,
        // 6, 7, 4,

        // // Right Face (using vertices 8-11)
        // 8, 9, 10,
        // 10, 11, 8,

        // // Left Face (using vertices 12-15)
        // 12, 13, 14,
        // 14, 15, 12,

        // // Top Face (using vertices 16-19)
        // 16, 17, 18,
        // 18, 19, 16,

        // // Bottom Face (using vertices 20-23)
        // 20, 21, 22,
        // 22, 23, 20 };

        // RawModel rawModel = loader.loadToVAO(vertices, textureCoords, indices);
        // TexturedModel texturedModel = new TexturedModel(rawModel, textures.get(0));
        // Entity entity = new Entity(texturedModel, new Vector3f(0, 0, 0), new
        // Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

        Camera camera = new Camera();

        boolean wireframe = false;

        while (!glfwWindowShouldClose(DisplayManager.window)) {
            double currentTime = glfwGetTime();
            deltaTime = (float) (currentTime - lastTime);
            lastTime = currentTime;
            camera.move(deltaTime);

            world.render();

            // renderer.processEntity(entity);

            if (Keyboard.isKeyPressed(GLFW_KEY_TAB)) {
                GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, wireframe ? GL11.GL_FILL : GL11.GL_LINE);
                wireframe = !wireframe;
            }

            renderer.render(camera);

            shader.stop();
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}