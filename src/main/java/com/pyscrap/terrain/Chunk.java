package com.pyscrap.terrain;

import com.pyscrap.Globals;
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

    Random random = new Random();

    MasterRenderer renderer;

    public Chunk(int xoffset, int zoffset, ModelTexture[] textures, MasterRenderer renderer,
            Loader loader, byte[] blockIDs) {
        this.renderer = renderer;

        for (int x = 0; x < Globals.CHUNK_LENGTH; x++) {
            for (int y = 0; y < Globals.CHUNK_HEIGHT; y++) {
                for (int z = 0; z < Globals.CHUNK_LENGTH; z++) {
                    ModelTexture texture = textures[2];

                    if (random.nextInt(10) == 3) {
                        texture = textures[4];
                    }

                    if (World.getBlockID(x, y, z, xoffset, zoffset) == 0) {
                        continue;
                    }

                    List<Float> vertices = new ArrayList<>();

                    if (x + (xoffset * Globals.CHUNK_LENGTH) + 1 >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_X
                            || World.getBlockID(x + 1, y, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveXVertices));
                    }

                    if (x + (xoffset * Globals.CHUNK_LENGTH) - 1 < 0
                            || World.getBlockID(x - 1, y, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeXVertices));
                    }

                    if (y + 1 >= Globals.CHUNK_HEIGHT || World.getBlockID(x, y + 1, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveYVertices));
                        texture = textures[1];
                    }

                    if (y - 1 < 0 || World.getBlockID(x, y - 1, z, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeYVertices));
                    }

                    if (z + (zoffset * Globals.CHUNK_LENGTH) + 1 >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z
                            || World.getBlockID(x, y, z + 1, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveZVertices));
                    }

                    if (z + (zoffset * Globals.CHUNK_LENGTH) - 1 < 0
                            || World.getBlockID(x, y, z - 1, xoffset, zoffset) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeZVertices));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices), BlockData.textureCoords,
                            BlockData.indices);
                    TexturedModel texturedModel = new TexturedModel(model, texture);

                    blocks.add(new Entity(texturedModel,
                            new Vector3f(x + (xoffset * Globals.CHUNK_LENGTH), y, z + (zoffset * Globals.CHUNK_HEIGHT)),
                            new Vector3f(0, 0, 0),
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
