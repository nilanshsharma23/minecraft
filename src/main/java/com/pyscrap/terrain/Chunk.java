package com.pyscrap.terrain;

import com.pyscrap.entities.Entity;
import com.pyscrap.models.RawModel;
import com.pyscrap.models.TexturedModel;
import com.pyscrap.renderEngine.Loader;
import com.pyscrap.renderEngine.MasterRenderer;
import com.pyscrap.textures.ModelTexture;
import com.pyscrap.toolbox.Methods;

import org.joml.Vector3f;

import java.util.*;

public class Chunk {
    List<Entity> blocks = new ArrayList<>();

    static final int HEIGHT = 32;
    static final int LENGTH = 32;
    static final int NO_OF_BLOCKS = HEIGHT * LENGTH * LENGTH;

    Random random = new Random();

    MasterRenderer renderer;

    public Chunk(int xoffset, int zoffset, ModelTexture[] textures, MasterRenderer renderer,
            Loader loader, byte[] blockIDs) {
        this.renderer = renderer;

        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int z = 0; z < LENGTH; z++) {
                    ModelTexture texture = textures[random.nextInt(textures.length)];

                    if (World.getBlockID(x, y, z, xoffset, zoffset) == 0) {
                        continue;
                    }

                    List<Float> vertices = new ArrayList<>();

                    if (x - 1 < 0 || World.getBlockID(x - 1, y, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeXVertices));
                    }

                    if (x + 1 >= LENGTH * 2 || World.getBlockID(x + 1, y, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveXVertices));
                    }

                    if (y - 1 < 0 || World.getBlockID(x, y - 1, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeYVertices));
                    }

                    if (y + 1 >= HEIGHT || World.getBlockID(x, y + 1, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveYVertices));
                    }

                    if (z - 1 < 0 || World.getBlockID(x, y, z - 1, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeZVertices));
                    }

                    if (z + 1 >= LENGTH * 2 || World.getBlockID(x, y, z + 1, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveZVertices));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices), BlockData.textureCoords,
                            BlockData.indices);
                    TexturedModel texturedModel = new TexturedModel(model, texture);

                    blocks.add(new Entity(texturedModel,
                            new Vector3f(x + (xoffset * LENGTH), y, z + (zoffset * LENGTH)), new Vector3f(0, 0, 0),
                            new Vector3f(1, 1, 1)));
                }
            }
        }
    }

    public void render() {
        for (Entity block : blocks) {
            renderer.processEntity(block);
        }
    }
}
