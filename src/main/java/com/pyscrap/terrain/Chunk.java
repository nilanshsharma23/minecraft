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

    public Chunk(int xoffset, int zoffset, List<ModelTexture> textures, MasterRenderer renderer,
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

                    List<Float> vertices = new ArrayList<>();

                    if (World.getBlockID(xOffsetted + 1, y, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveXVertices));
                    }

                    if (World.getBlockID(xOffsetted - 1, y, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeXVertices));
                    }

                    if (World.getBlockID(xOffsetted, y + 1, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveYVertices));
                    }

                    if (World.getBlockID(xOffsetted, y - 1, zOffsetted) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeYVertices));
                    }

                    if (World.getBlockID(xOffsetted, y, zOffsetted + 1) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.positiveZVertices));
                    }

                    if (World.getBlockID(xOffsetted, y, zOffsetted - 1) == 0) {
                        vertices.addAll(Arrays.asList(BlockData.negativeZVertices));
                    }

                    RawModel model = loader.loadToVAO(Methods.FloatListToArray(vertices), BlockData.textureCoords,
                            BlockData.indices);
                    TexturedModel texturedModel = new TexturedModel(model,
                            textures.get(World.getBlockID(xOffsetted, y, zOffsetted) - 1));

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
