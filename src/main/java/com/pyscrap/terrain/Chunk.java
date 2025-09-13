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
            Loader loader) {
        this.renderer = renderer;

        for (int x = 0; x < Globals.CHUNK_LENGTH; x++) {
            for (int z = 0; z < Globals.CHUNK_LENGTH; z++) {
                for (int y = 0; y < Globals.CHUNK_HEIGHT; y++) {

                    int xOffsetted = x + (xoffset * Globals.CHUNK_LENGTH);
                    int zOffsetted = z + (zoffset * Globals.CHUNK_LENGTH);

                    if (World.getBlockID(xOffsetted, y, zOffsetted) == 0) {
                        continue;
                    }

                    ModelTexture texture = textures[2];

                    if (random.nextInt(10) == 3) {
                        texture = textures[4];
                    }

                    List<Float> vertices = new ArrayList<>();

                    if (xOffsetted + 1 >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_X
                            || World.getBlockID(xOffsetted + 1, y, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveXVertices));
                    }

                    if (xOffsetted - 1 < 0
                            || World.getBlockID(xOffsetted - 1, y, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeXVertices));
                    }

                    if (y + 1 >= Globals.CHUNK_HEIGHT || World.getBlockID(xOffsetted, y + 1, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveYVertices));
                        texture = textures[1];
                    }

                    if (y - 1 < 0 || World.getBlockID(xOffsetted, y - 1, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeYVertices));
                    }

                    if (zOffsetted + 1 >= Globals.CHUNK_LENGTH * Globals.NO_OF_CHUNKS_Z
                            || World.getBlockID(xOffsetted, y, zOffsetted + 1) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveZVertices));
                    }

                    if (zOffsetted - 1 < 0
                            || World.getBlockID(xOffsetted, y, zOffsetted - 1) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeZVertices));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices), BlockData.textureCoords,
                            BlockData.indices);
                    TexturedModel texturedModel = new TexturedModel(model, texture);

                    blocks.add(new Entity(texturedModel,
                            new Vector3f(xOffsetted, y, zOffsetted),
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
