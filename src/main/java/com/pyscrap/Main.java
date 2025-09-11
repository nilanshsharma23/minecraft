package com.pyscrap;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import com.pyscrap.entities.Camera;
import com.pyscrap.entities.Cube;
import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.DisplayManager;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.Renderer;
import com.pyscrap.shaders.StaticShader;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.Methods;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        float[] textureCoords = {

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,

                0f,0f,
                0f,1f,
                1f,1f,
                1f,0f,
        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
        };


        ModelTexture texture = new ModelTexture(loader.loadTexture("texture"));

        List<Entity> entities = new ArrayList<>();
        List<List<List<Boolean>>> existence = new ArrayList<>();

        int size = 16;

        for (int x = 0; x < size; x++) {
            existence.add(new ArrayList<>());
            for (int y = 0; y < size; y++) {
                existence.get(x).add(new ArrayList<>());
                for (int z = 0; z < size; z++) {
                    existence.get(x).get(y).add(true);
                }
            }
        }

        for (int x = 0; x < existence.size(); x++) {
            for (int y = 0; y < existence.get(x).size(); y++) {
                for (int z = 0; z < existence.get(x).get(y).size(); z++) {
                    if (!existence.get(x).get(y).get(z)){
                        continue;
                    }

                    List<Float> vertices = new ArrayList<>();

                    if (x - 1 < 0 || !existence.get(x - 1).get(y).get(z)) {
                        vertices.addAll(Arrays.asList(Cube.negativeXVertices));
                    }

                    if (x + 1 >= size || !existence.get(x + 1).get(y).get(z)){
                        vertices.addAll(Arrays.asList(Cube.positiveXVertices));
                    }

                    if (y - 1 < 0 ||!existence.get(x).get(y - 1).get(z)){
                        vertices.addAll(Arrays.asList(Cube.negativeYVertices));
                    }

                    if (y+1 >= size || !existence.get(x).get(y+1).get(z)){
                        vertices.addAll(Arrays.asList(Cube.positiveYVertices));
                    }

                    if (z-1 < 0 || !existence.get(x).get(y).get(z-1)){
                        vertices.addAll(Arrays.asList(Cube.negativeZVertices));
                    }

                    if (z+1 >= size || !existence.get(x).get(y).get(z+1)){
                        vertices.addAll(Arrays.asList(Cube.positiveZVertices));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices), textureCoords, indices);
                    TexturedModel texturedModel = new TexturedModel(model, texture);
                    entities.add(new Entity(texturedModel, new Vector3f(x, y, z), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1)));
                }
            }
        }

        Camera camera = new Camera();

        while (!glfwWindowShouldClose(DisplayManager.window)) {
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            for (Entity entity : entities){
                renderer.render(entity, shader);
            }
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}