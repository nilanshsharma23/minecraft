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