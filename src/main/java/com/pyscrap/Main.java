package com.pyscrap;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_TAB;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

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

        ModelTexture dirtTexture = new ModelTexture(loader.loadTexture(0, 0));
        ModelTexture grassTexture = new ModelTexture(loader.loadTexture(1, 0));
        ModelTexture stoneTexture = new ModelTexture(loader.loadTexture(2, 0));
        ModelTexture cobblestoneTexture = new ModelTexture(loader.loadTexture(0, 1));
        ModelTexture coalTexture = new ModelTexture(loader.loadTexture(3, 0));
        ModelTexture waterTexture = new ModelTexture(loader.loadTexture(1, 1));
        ModelTexture[] textures = { dirtTexture, grassTexture, stoneTexture, cobblestoneTexture, coalTexture,
                waterTexture };
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